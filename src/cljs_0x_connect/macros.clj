(ns cljs-0x-connect.macros
  (:require [camel-snake-kebab.core :as camel-snake]
            [camel-snake-kebab.extras :as camel-snake-extras]
            [cljs.core]))

(defn defsignature [sig args body]
  `(defn ~(symbol (camel-snake/->kebab-case sig)) ~args ~@body))

(defmacro defsignatures [sigs]
  (cons `do
        (for [[sig [client request & [opts]]] sigs]
          (defsignature
            sig
            '[client request & [opts]]
            `((cljs.core/js-invoke (apply cljs.core/js-invoke ~'client ~sig (remove nil? [(->> ~'request
                                                                                               (camel-snake-extras/transform-keys camel-snake/->camelCase)
                                                                                               cljs.core/clj->js)
                                                                                          (when ~'opts
                                                                                            (cljs.core/clj->js ~'opts))]))
                                   "then" #(->> %
                                                cljs.core/js->clj
                                                (camel-snake-extras/transform-keys camel-snake/->kebab-case-keyword))))))))
