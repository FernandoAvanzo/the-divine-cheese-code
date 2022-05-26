(ns the-divine-cheese-code.visualization.svg
  (:refer-clojure :exclude [max min])
  (:require [clojure.string]))

(defn latlng->point
  "Convert lat/lng map to comma-separated string"
  [latlng]
  (str (:lng latlng) " , " (:lat latlng)))

(defn points
  [locations]
  (clojure.string/join " " (map latlng->point locations)))

(defn comparator-over-maps
  [comparison-fn ks]
  (fn [maps]
    (zipmap
      ks (map
           (fn [k]
             (apply comparison-fn (map k maps)))))))

(def min (comparator-over-maps clojure.core/min [:lat :lng]))
(def max (comparator-over-maps clojure.core/max [:lat :lng]))
