Nitu Nicolae Iulian TEMA3 POO

La codul de la etapa1, am adaugat pachetul Angels, pachetul observer si pachetul strategies.

In pachetul Angels am creat o interfata Angel si implementez Visitor Pattern.
    Cu ajutorul interfetei Angel implementez fiecare inger , care va vizita fiecare erou si ii va modifica hp/exp/dmg etc.
    Am realizat si un AngelFactory

In pachetul Strategies:
    Am creat o interfata comuna pt strategii
    Cele 8 clase implementeaza strategiile conform cerintei.

In pachetul Observer:
    In clasa Observer implementez observer design pattern. Unde am facut overload pe metoda update pt fiecare situatie.
    Spre ex, daca apelez functia update cu 2 playeri inseamna ca unul l-a omorat pe celalalt. Daca apelez functia cu un
    player si cu un inger, inseamna ca acel inger a ajutat/incurcat respectivul jucator, etc.
    Fiecare inger este observat de catre Observer. Toti jucatorii sunt observati de catre Observer.

In pachetul Hero:
    Am adaugat un HeroFactory

In main:
    Am facut harta Singleton
    In main, inputul este citit in clasa GameInput. Apoi jucatorii iau dmg overTime, incerc sa ii mut daca nu au stun,
    setez landModifierul, aplic strategia corespunzatoare. Daca 2 jucatori se afla pe aceiasi pozitie, atunci ei se vor
    batea. Dupa ce se bat, vin ingerii care ii ajuta/ incurca si runda se incheie.

Am 18 erori de CS, toate din cauza campurilor publice din clasa Hero.

