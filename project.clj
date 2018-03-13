(defproject cljs-0x-connect "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.145"]
                 [org.clojure/tools.reader "1.2.2"]
                 [thheller/shadow-cljs "2.2.7"]]

  :exclusions [[org.clojure/tools.reader]]

  :source-paths ["src"]
  :clean-targets ^{:protect false} ["target" "public/js" "node_modules" ".shadow-cljs"]
  :profiles {:dev {:source-paths ["dev" "src"]
                   :resource-paths ["assets"]
                   :plugins [[cider/cider-nrepl "0.16.0"]]
                   :repl-options {:init-ns ^:skip-aot user
                                  :nrepl-middleware  [shadow.cljs.devtools.server.nrepl/cljs-load-file
                                                      shadow.cljs.devtools.server.nrepl/cljs-eval
                                                      shadow.cljs.devtools.server.nrepl/cljs-select]}}})
