(defproject alfa "0.1.0-SNAPSHOT"
  :dependencies
  [[org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "1.7.122"]
   [lib-noir "0.9.9"]
   [com.ashafa/clutch "0.4.0"]
   [zenedu/cdbview "0.1.0"]
   [ring/ring-defaults "0.1.5"]
   [ring/ring-core "1.4.0"]
   [selmer "0.9.1"]
   [org.immutant/web "2.1.0"]
   [reagent "0.5.0"]
   [re-frame "0.4.1"]
   [cljs-ajax "0.3.14"]
   [secretary "1.2.3"]]

  :source-paths
  ["src/clj"]

  :resource-paths ["resources"]

  :plugins
  [[lein-cljsbuild "1.1.0"]
   [lein-figwheel "0.3.9" :exclusions [cider/cider-nrepl]]]

  :clean-targets
  ^{:protect false}
  ["resources/public/js/compiled" "target"]

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "alfa.core/mount-root"}
     :compiler     {:main                 alfa.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main          alfa.core
                    :output-to     "resources/public/js/compiled/app.js"
                    :optimizations :advanced
                    :pretty-print  false}}]})
