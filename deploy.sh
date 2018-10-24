#!/usr/bin/env bash

kubectl apply -f etc/dev/load-balancer.yml
kubectl apply -f etc/dev/pod.yml