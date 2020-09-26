#!/bin/bash
docker build -t trend-hotspot -f Dockerfile.openjdk .
docker build -t trend-openj9 -f Dockerfile.openj9 .
docker build -t trend-openj9-warmed -f Dockerfile.openj9.warmed .
