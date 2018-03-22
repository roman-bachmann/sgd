#!/usr/bin/env bash
set -e

# Resource: https://archive.ics.uci.edu/ml/datasets/Reuters+RCV1+RCV2+Multilingual,+Multiview+Text+Categorization+Test+collection

DIR=$(dirname `realpath $0`)
FILENAME=rcv1rcv2aminigoutte.tar.bz2

if [ -f $DIR/$FILENAME ]; then
    echo '->>> Cool, the file is already here!';
else
    echo '->>> Download the dataset, this might take a while'
    wget https://archive.cdics.uci.edu/ml/machine-learning-databases/00259/$FILENAME -p $DIR
fi

echo '->>> Unzip...'
tar -xf $FILENAME
