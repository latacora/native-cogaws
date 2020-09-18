(ns latacora.native-cogaws
  (:require
   [cognitect.aws.http :as http]
   [cognitect.aws.http.cognitect :as anothe]
   [cognitect.aws.client.api :as aws])
  (:gen-class))

(set! *warn-on-reflection* true)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World! - I'm a native thing!")
  (let [s3 (aws/client {:api :s3})]
    (println "s3 client has been initialized")
    )
  )
