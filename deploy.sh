#!/usr/bin/env bash
kubectl apply -f ./etc/dev/load-balancer.yaml
kubectl apply -f ./etc/dev/pod.yaml