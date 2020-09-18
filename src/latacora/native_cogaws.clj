(ns latacora.native-cogaws
  (:require
   [cognitect.aws.http :as http]
   [cognitect.aws.http.cognitect :as anothe]
   [cognitect.aws.client.api :as aws])
  (:gen-class))

(set! *warn-on-reflection* true)


;; http/HttpClient
;; (http/client? nil)
;; (http/)
((let [client  ( http/resolve-http-client nil)]
   client))

(aws/default-http-client)
(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World! - I'm a native thing!")
  (aws/default-http-client)
  (let [s3 (aws/client {:api :s3})]
    (println "s3 client has been initialized")
    ))
