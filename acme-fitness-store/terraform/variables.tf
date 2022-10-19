# Variable Definition 
variable "project_name" {
  type        = string
  default     = "acme-fitness"
  description = "Project Name"
}

variable "resource_group_location" {
  type        = string
  default     = "West Europe"
  description = "Azure Resource Group"
}

variable "order_service_db_name" {
  type    = string
  default = "acmefit_order"
}

variable "catalog_service_db_name" {
  type    = string
  default = "acmefit_catalog"
}

variable "sso-jwk-uri" {
  type        = string
  description = "SSO Provider JWK-URI"
}