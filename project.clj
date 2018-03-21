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

  :plugins [[lein-npm "0.6.2"]
            [lein-figwheel "0.5.14"]
            [lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.8"]]

  :npm {:devDependencies [[karma "1.7.1"]
                          [karma-chrome-launcher "2.2.0"]
                          [karma-cli "1.0.1"]
                          [karma-cljs-test "0.1.0"]]}

  :doo {:paths {:karma "./node_modules/karma/bin/karma"}}

  :repl-options {:init-ns ^:skip-aot user}

  :figwheel {:server-port 8080}

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "tests-output"]

  :profiles {:dev {:source-paths ["dev"]
                   :resource-paths ["resources"]
                   :dependencies [[com.cemerick/piggieback "0.2.2"]
                                  [figwheel-sidecar "0.5.14"]
                                  [org.clojure/clojure "1.8.0"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [lein-doo "0.1.8"]]}}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel {:on-jsload "cljs-0x-connect.dev/start"}
                        :compiler {:main "cljs-0x-connect.dev"
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true
                                   :closure-defines {goog.DEBUG true}

                                   :install-deps true
                                   :npm-deps {:websocket "1.0.25"}

                                   :external-config {:devtools/config {:features-to-install :all}}}}
                       {:id "tests"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "tests-output/tests.js"
                                   :output-dir "tests-output"
                                   :main "tests.runner"
                                   :optimizations :none}}]})
