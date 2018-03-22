(defproject district0x/cljs-0x-connect "0.0.1"
  :description "cljs wrapper around 0xproject/connect library"
  :url "https://github.com/district0x/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[camel-snake-kebab "0.4.0"]
                 [cljsjs/zeroxproject-connect "0.6.6-0"]
                 [org.clojure/clojurescript "1.10.191"]]

  :exclusions [[org.clojure/clojure]
               [org.clojure/clojurescript]]

  :npm {:devDependencies [[karma "1.7.1"]
                          [karma-chrome-launcher "2.2.0"]
                          [karma-cli "1.0.1"]
                          [karma-cljs-test "0.1.0"]]}

  :doo {:paths {:karma "./node_modules/karma/bin/karma"}}

  :clean-targets ^{:protect false} ["target" "tests-output"]

  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [lein-doo "0.1.8"]]
                   :plugins [[lein-cljsbuild "1.1.7"]
                             [lein-doo "0.1.8"]
                             [lein-npm "0.6.2"]]
                   :repl-options {:init-ns ^:skip-aot user}}}

  :cljsbuild {:builds [{:id "tests"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "tests-output/tests.js"
                                   :output-dir "tests-output"
                                   :main "tests.runner"
                                   :optimizations :none}}]})
