(defproject cljs-0x-connect "1.0.0-SNAPSHOT"
  :description "cljs wrapper around 0xproject/connect library"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[camel-snake-kebab "0.4.0"]
                 [org.clojure/clojurescript "1.10.145"]
                 [thheller/shadow-cljs "2.2.8"]]

  :exclusions [[org.clojure/clojurescript]
               [org.clojure/clojure]]

  :source-paths ["src" "test"]

  :clean-targets ^{:protect false} ["target"]

  :profiles {:dev {:dependencies [[funcool/promesa "1.9.0"]
                                  [binaryage/devtools "0.9.7"]
                                  [org.clojure/clojure "1.9.0"]]
                   :plugins [[cider/cider-nrepl "0.16.0"]]
                   :source-paths ["dev"]
                   :repl-options {:init-ns ^:skip-aot user
                                  :nrepl-middleware [shadow.cljs.devtools.server.nrepl/cljs-load-file
                                                     shadow.cljs.devtools.server.nrepl/cljs-eval
                                                     shadow.cljs.devtools.server.nrepl/cljs-select]}}})
