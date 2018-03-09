(ns user
  (:require [shadow.cljs.devtools.api]
            [shadow.cljs.devtools.server]))

(defn start-dev! []
  (do (shadow.cljs.devtools.server/start!)
      (shadow.cljs.devtools.api/watch :dev)
      (shadow.cljs.devtools.api/nrepl-select :dev)))
