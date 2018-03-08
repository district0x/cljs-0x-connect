(ns cljs-0x-connect.dev
  (:require [cljs-0x-connect.http-client :as http-client]))

(def debug?
  ^boolean js/goog.DEBUG)

(defn dev-setup []
  (when debug?
    (enable-console-print!)
    (println "dev mode")))
