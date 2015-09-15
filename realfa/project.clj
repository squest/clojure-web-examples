(defproject realfa "0.1.0-SNAPSHOT"
  :dependencies
  [[org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "1.7.122"]
   [lib-noir "0.9.9"]
   [http-kit "2.1.19"]
   [com.ashafa/clutch "0.4.0"]
   [selmer "0.9.1"]
   [cljs-ajax "0.3.14"]
   [reagent "0.5.1"]
   [re-frame "0.4.1"]
   [secretary "1.2.3"]
   [ring/ring-defaults "0.1.5"]
   [ring "1.4.0"]]

  :source-paths
  ["src/clj"]

  :plugins
  [[lein-cljsbuild "1.1.0"]
   [lein-figwheel "0.3.9" :exclusions [cider/cider-nrepl]]]

  :clean-targets
  ^{:protect false} ["resources/public/js/compiled" "target"]

  :resource-paths
  ["resources"]

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]

     :figwheel     {:on-jsload "realfa.core/mount-root"}

     :compiler     {:main                 realfa.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main          realfa.core
                    :output-to     "resources/public/js/compiled/app.js"
                    :optimizations :advanced
                    :pretty-print  false}}]})
