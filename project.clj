(defproject cljs-0x-connect "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.145"]
                 [org.clojure/tools.reader "1.2.1"]
                 [thheller/shadow-cljs "2.2.6"]]

  :exclusions [[org.clojure/tools.reader]]

  :source-paths ["src"]
  :clean-targets ^{:protect false} ["target" "node_modules"]
  :profiles

  {:dev {:source-paths ["dev" "src"]
         :resource-paths ["assets"]
         :dependencies [[binaryage/devtools "0.9.9"]
                        ;;[com.cemerick/piggieback "0.2.2"]
                        ;;[org.clojure/clojure "1.9.0"]
                        ]
         :plugins [[cider/cider-nrepl "0.16.0"]]
         :repl-options {:init-ns ^:skip-aot user
                        :nrepl-middleware  [shadow.cljs.devtools.server.nrepl/cljs-load-file
                                            shadow.cljs.devtools.server.nrepl/cljs-eval
                                            shadow.cljs.devtools.server.nrepl/cljs-select
                                            ;; required by some tools, not by shadow-cljs.
                                            ;;cemerick.piggieback/wrap-cljs-repl
                                            ]}}})
