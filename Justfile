default:
    just --list
    just testall

testall:
    ./gradlew clean test

watch lab:
    watchexec -e java,gradle -c -r just {{lab}}

runtest subdir: (test subdir) (run subdir)

run subdir:
    cd {{subdir}} && ../gradlew run

test subdir:
    cd {{subdir}} && ../gradlew test

clean:
    fd -I -td build -x rm -r


lab2:
    just runtest EDAA30-Lab2-Sched
    just runtest EDAA30-Lab2-Nils

lab5:
    just runtest EDAA30-Lab5

lab6:
    just runtest EDAA30-Lab6

tele1:
    just runtest EITG01-Lab1

tele2:
    just runtest EITG01-Lab2
