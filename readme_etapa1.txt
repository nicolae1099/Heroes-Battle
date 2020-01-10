NITU NICOLAE IULIAN 321CA TEMA2 POO

In main ma folosesc de clasele GameInputLoader si GameInput pt a citi input din fisier.
La inceputul fiecarei runde, setez multiplicatorul de teren si jucatorii primesc damage over time.
Apoi mut toti jucatorii. Daca 2 jucatori ajung pe aceiasi parcela, ei se vor batea. ( functia battle).

In functia Battle() din clasa Arena, se calculeaza daunele care urmeaza sa le dea un jucator contra celuilalt jucator.
In functia Battle() folosesc functia fight() care apeleaza metoda isAttackedBy(). Scopul metodei isAttackedBy() este
returnarea race multiplier-ului. Oponentul este atacat de prima abilitate a atacatorului si apoi se calculeaza un damage
, la care se va adauga damage ul obtinut cu race multiplierul de la a 2a abilitate.
Functia battle() mai foloseste si functia applyFirstAbility() care adauga un efect ( damage over time, paralysis, stun)
La fel si pt applySecondAbility();

In pachetul abilities implementez Visitor Design Pattern.

In pachetul heroes am clasa Constants unde retin constante pt cei 4 eroi
Clasa Hero este o clasa abstracta care urmeaza sa fie extinsa de Knight, Rogue, Wizard, Pyromancer
Clasa Hero are o metoda a face levelUp unui player si o metoda de a muta jucatorul la o noua pozitie.

metoda applyFirstAbility si applySecondAbility e implementata diferit in clasa fiecarui erou.
In clasa Wizzard la applySecondAbility() creez un erou nou, simular cu oponentul, ca sa simulez atacul oponentului
fara sa modific totalDamage ul oponentului.


