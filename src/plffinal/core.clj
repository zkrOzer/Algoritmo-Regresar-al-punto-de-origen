(ns plffinal.core)

(defn regresan
  []
  (hash-map
   \< 1
   \> -1
   \^ 1
   \v -1))

(defn regresan2
  []
  (hash-map
   \< \>
   \> \<
   \^ \v
   \v \^))

;;1

(defn inter
  [xs]
  (let [tabla (regresan)
        comparar (fn [c] (let [v (tabla c)] (if (int? v) v c)))]
    (flatten(map (fn [s] (map comparar s)) (vector xs)))))

(defn regresa-al-punto-de-origen?
  [s]
(if (zero? (reduce + (inter s)))
  true
  false))

(regresa-al-punto-de-origen? (list \> \<))

;;2

(defn remplazar
  [xs]
  (let [tabla (regresan)
        comparar (fn [c] (let [v (tabla c)] (if (int? v) v c)))]
    (map (fn [s] (map comparar s)) xs)))

(defn regresan-al-punto-de-origen?
  [x]
(if (zero? (reduce + (flatten (remplazar x))))
  true
  false))

(regresan-al-punto-de-origen? (vector ">>>" "^vv^" "<<>>"))

;;3

(defn regreso-al-punto-de-origen
  [xs]
  (let [tabla (regresan2)
        comparar (fn [c] (let [v (tabla c)] (if (char? v) v c)))]
    (flatten(map (fn [s] (map comparar s)) (vector xs)))))

(regreso-al-punto-de-origen [\< \v \v \v \> \>])
(= (list \< \< \<) (regreso-al-punto-de-origen ">>>"))

;;4

(defn mismo-punto-final?
  [a b]
(if (= (reduce + (flatten (remplazar (vector a)))) (reduce + (flatten (remplazar (vector b)))))
  true
  false))

(mismo-punto-final? [\> \> \>] [\> \> \> \>])

;;5


(defn coincidencias
  [s]
  (letfn [(f [x]
            (cond (empty? x)
                  1
                  :else (+ (if (and (= \< (first x)) (= \< (first (rest x))))
                             1
                             0)
                           (if (and (= \> (first x)) (= \> (first (rest x))))
                             1
                             0)
                           (if (and (= \^ (first x)) (= \^ (first (rest x))))
                             1
                             0)
                           (if (and (= \v (first x)) (= \v (first (rest x))))
                             1
                             0)
                           (f (rest (drop 1 x))))))]
    (f s)))

(coincidencias (apply str "<<vv>>^>>" "vv<^"))





