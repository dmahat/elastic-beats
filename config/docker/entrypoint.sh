#!/usr/bin/env bash
service filebeat start
echo "$*"
/bin/sh -c "$*"