(defproject district0x/cljs-0x-connect "0.0.1"
  :description "cljs wrapper around @0xproject/connect library"
  :url "https://github.com/district0x/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojurescript "1.10.145"]]

  :exclusions [[org.clojure/clojure]
               [org.clojure/clojurescript]]

  :min-lein-version "2.5.3"

  :plugins [[lein-figwheel "0.5.15"]]

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled"]

  :repl-options {:init-ns ^:skip-aot user}

  :profiles {:dev {:source-paths ["dev"]
                   :resource-paths ["resources"]
                   :dependencies [[binaryage/devtools "0.9.9"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [figwheel-sidecar "0.5.15"]
                                  [org.clojure/clojure "1.8.0"]
                                  [org.clojure/tools.nrepl "0.2.13"]]
                   :plugins [[lein-cljsbuild "1.1.7"]
                             [lein-doo "0.1.8"]]}}

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel {:on-jsload "cljs-0x-connect.dev/dev-setup"}
                        :compiler {:main "cljs-0x-connect.dev"
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true
                                   :optimizations :none
                                   :closure-defines {goog.DEBUG true}
                                   :external-config {:devtools/config {:features-to-install :all}}
                                   :install-deps true
                                   :npm-deps {"@0xproject/connect" "0.6.2"}}}]})
