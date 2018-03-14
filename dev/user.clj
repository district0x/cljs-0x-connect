(ns user
  (:require [shadow.cljs.devtools.api]
            [shadow.cljs.devtools.server]))

(defn start-dev! []
  (shadow.cljs.devtools.server/start!)
  (shadow.cljs.devtools.api/watch :app)
  (shadow.cljs.devtools.api/nrepl-select :app))
