provider "aws" {
  region = "${var.aws_region}"
}

terraform {
  backend "s3" {
    bucket = "tf-eid-validator"
    key    = "terraform"
    region = "us-east-1"
  }
}

resource "aws_ecr_repository" "ecr_repository" {
  name = "${var.repository}"
}

output "respository_url" {
  value = "${aws_ecr_repository.ecr_repository.repository_url}"
}