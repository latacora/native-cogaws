(ns latacora.native-cogaws
  (:require
   [cheshire.core :as json]
   [cognitect.aws.http.cognitect]
   [cognitect.aws.protocols.rest-xml]
   [cognitect.aws.client.api :as aws])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World! - I'm a native thing!")
  (let [s3 (aws/client {:api :s3})
        bucket "latacora-webhook-logs-test"]
    (println "s3 client has been initialized")
    (println "here are your buckets: ")
    (println (aws/invoke s3 {:op :ListBuckets}))
    (println "saving a file")
    (println (aws/invoke s3 {:op :PutObject :request {:Bucket bucket
                                                      :Key "test/out"
                                                      :Body (.getBytes (json/generate-string "here is some json!"))}}))
    (println (aws/invoke s3 {:op :ListBuckets}))
    ))

