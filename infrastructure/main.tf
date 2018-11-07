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

module  "rds" {
  source = "./rds"
  eid_db_password = "${var.eid_db_password}"
}

resource "aws_ecr_repository" "ecr_repository" {
  name = "${var.repository}"
}

output "respository_url" {
  value = "${aws_ecr_repository.ecr_repository.repository_url}"
}

output "db_password" {
  value = "${var.eid_db_password}"
}