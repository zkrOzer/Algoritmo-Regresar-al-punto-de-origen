(defproject plffinal "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [lambdaisland/kaocha "1.0.732" :exclusions [org.clojure/spec.alpha]]
                 [lambdaisland/kaocha-cloverage "0.0-32"]
                 [clj-kondo "2020.11.07"]]
  :plugins [[jonase/eastwood "0.3.11"]]
  :aliases {"kaocha" ["run" "-m" "kaocha.runner" "--color" "--reporter" "documentation"]
            "cloverage" ["run" "-m" "kaocha.runner" "--plugin" "cloverage" "--no-cov-html" "--cov-summary"]
            "clj-kondo" ["run" "-m" "clj-kondo.main" "--lint" "src:test"]}
  :main ^:skip-aot plf08.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
