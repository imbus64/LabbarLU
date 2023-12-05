default:
    just --list
    just test

test:
    ./gradlew test

watch lab:
    watchexec -e java,gradle -c -r just {{lab}}

run subdir:
    cd {{subdir}} && ../gradlew run

lab2:
    just run EDAA30-Lab2-Sched
    just run EDAA30-Lab2-Nils

lab5:
    just run EDAA30-Lab5

lab6:
    just run EDAA30-Lab6

tele1:
    just run EITG01-Lab1

tele2:
    just run EITG01-Lab2
