#!/usr/bin/env bash
aws-iam-authenticator init -i $CLUSTER_NAME
kubectl --kubeconfig heptio-authenticator-aws.kubeconfig
kubectl apply -f ./etc/dev/load-balancer.yaml
kubectl apply -f ./etc/dev/pod.yaml