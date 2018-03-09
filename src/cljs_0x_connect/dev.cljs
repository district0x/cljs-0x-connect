(ns cljs-0x-connect.dev
  (:require [devtools.core :as devtools]
            [cljs-0x-connect.http-client :as http-client]))

(defn reload! []
  (devtools/install!)
  (console.log "dev mode"))
