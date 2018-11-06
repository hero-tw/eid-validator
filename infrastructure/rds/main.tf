resource "random_string" "password" {
  length = 16
  special = false
}

resource "aws_default_vpc" "default" {
}

resource "aws_default_security_group" "default" {
  vpc_id = "${aws_default_vpc.default.id}"
}

resource "aws_db_instance" "eid_db" {
  identifier             = "eid-validator-mysql"
  allocated_storage      = "10"
  engine                 = "mysql"
  engine_version         = "8.0"
  instance_class         = "db.t2.micro"
  name                   = "eid"
  username               = "eid"
  port                   = "5432"
  skip_final_snapshot    = true
  final_snapshot_identifier = "eid-validator-mysql-final-snapshot"
  password               = "${random_string.password.result}"
  vpc_security_group_ids = ["${aws_default_security_group.default.id}"]
}
