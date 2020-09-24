resource "aws_s3_bucket" "test-bucket" {
  bucket = "latacora-webhook-logs-test"
  acl    = "private"
  force_destroy = true
}
