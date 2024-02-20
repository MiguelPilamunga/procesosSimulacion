FROM ubuntu:latest
LABEL authors="labctrl"

ENTRYPOINT ["top", "-b"]