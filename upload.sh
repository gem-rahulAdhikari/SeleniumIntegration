#!/bin/bash

# Set your Google Cloud Storage bucket name and destination path in the bucket
BUCKET_NAME="selenium-output"
directory="/home/gemcodeeditor/$1/selenium_Integartion/test-output"
file_extension=".html"
filename=$(find "$directory" -maxdepth 1 -type f -name "*$file_extension" | grep "$file_extension")
echo "Found file: $filename"
# Replace '/path/to/local/file.html' with the actual path to your HTML file on the VM
gsutil cp $filename gs://${BUCKET_NAME}
