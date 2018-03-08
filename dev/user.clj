(ns user
  (:require [figwheel-sidecar.repl-api :as repl-api]
            [figwheel-sidecar.config :as config]))

(defn start-repl! []
  (repl-api/start-figwheel! (config/fetch-config)
                            #_(assoc-in (config/fetch-config)
                                        [:data :figwheel-options :server-port] 4040)
                            "dev")
  (repl-api/cljs-repl "dev"))
