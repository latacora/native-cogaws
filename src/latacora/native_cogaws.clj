(ns latacora.native-cogaws
  (:require
   [cheshire.core :as json]
   [cognitect.aws.http.cognitect]
   [cognitect.aws.protocols.rest-xml]
   [cognitect.aws.client.api :as aws])
  (:gen-class))

(set! *warn-on-reflection* true)

;; (let [s3 (aws/client {:api :s3})
;;       bucket "latacora-webhook-logs-test"]


;;   (aws/invoke s3 {:op :ListBuckets})
;;   (aws/invoke s3 {:op :ListObjectsV2 :request {
;;                                                :Bucket bucket
;;                                                }})
;;   )
(defmacro rethrow
  "Convenience macro to rethrow errors from
  cognitect.aws.client.api. Macro so that it doesn't add to the
  stacktrace."
  [aws-response]
  `(let [response# ~aws-response
         ;; two different keywords for throwables >:(
         throwable# (or (:cognitect.aws.http.cognitect/throwable response#)
                        (:throwable response#))]
     (if throwable#
       (throw throwable#)
       response#)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World! - I'm a native thing!")
  (let [s3 (aws/client {:api :s3})
        bucket "latacora-webhook-logs-test"]
    (println "s3 client has been initialized")
    (println "here are your buckets: ")
    (println (rethrow (aws/invoke s3 {:op :ListBuckets})))
    (println "saving a file")
    (println (rethrow (aws/invoke s3 {:op :PutObject :request {:Bucket bucket
                                                               :Key "test/out"
                                                               :Body (.getBytes (json/generate-string "here is some json!"))}})))  (println (aws/invoke s3 {:op :ListBuckets}))
    ))

