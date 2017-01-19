#Sebastian Przybył 
option solver minos;
reset;

param gotowka  ;
param reklama ;

param minSprzedaz ;
param maxSprzedaz ;

param minJakosc ;
param maxJakosc ;

param ryzyko ;#= 0.5;  prawdopodobieństwo ustalane w trzech wariantach


#data data.data;

var jakosc >= 25;

var cenaP = 8.8 + jakosc * 0.005 * 8.8; # cena produkcji 
var cenaS ;
var prawdS = (1-reklama/gotowka)*((cenaP/cenaS));

var produkcja= (gotowka-reklama)/cenaP;
var sprzedaz ;#=prawdS*produkcja;

var zysk=prawdS*produkcja*(cenaS-cenaP)-reklama;
maximize f_celu: zysk;

s.t. prawd : prawdS>=ryzyko;
s.t. ogrProd : 0<=produkcja <=400000;
s.t. jak : jakosc <= 100;
s.t. cena : cenaP <= cenaS;
s.t. cena2 : cenaS >= minSprzedaz;
s.t. jakoscO : jakosc >=minJakosc;
s.t. jakoscO2 : jakosc <=maxJakosc;
#solve;

#display produkcja,cenaS,sprzedaz,cenaP,f_celu, jakosc,prawdS;