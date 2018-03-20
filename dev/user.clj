(ns user
  (:require [figwheel-sidecar.repl-api :as repl-api]
            [figwheel-sidecar.config :as config]))

(defn start! []
  (repl-api/start-figwheel! (assoc-in (config/fetch-config)
                                      [:data :figwheel-options :server-port] 8080)
                            "dev")
  (repl-api/cljs-repl "dev"))
