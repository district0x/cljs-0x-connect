(ns cljs-0x-connect.macros
  (:require [camel-snake-kebab.core :as camel-snake]
            [camel-snake-kebab.extras :as camel-snake-extras]
            [cljs.core]))

(defn defsignature [sig args body]
  `(defn ~(symbol (camel-snake/->kebab-case sig)) ~args ~@body))

(defmacro defsignatures [sigs]
  (cons `do
        (for [[sig [{:keys [:request :opts]}]] sigs]
          (defsignature
            sig
            '[client & [{:keys [:request :opts]}]]
            `((cljs.core/js-invoke (apply cljs.core/js-invoke ~'client ~sig (remove nil? [(when ~'request
                                                                                            (->> ~'request
                                                                                                 (camel-snake-extras/transform-keys camel-snake/->camelCase)
                                                                                                 cljs.core/clj->js))
                                                                                          (when ~'opts
                                                                                            (->> ~'opts
                                                                                                 (camel-snake-extras/transform-keys camel-snake/->camelCase)
                                                                                                 cljs.core/clj->js))]))
                                   "then" #(->> %
                                                cljs.core/js->clj
                                                (camel-snake-extras/transform-keys camel-snake/->kebab-case-keyword))))))))
