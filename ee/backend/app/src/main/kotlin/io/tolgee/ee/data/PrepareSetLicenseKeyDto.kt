package io.tolgee.ee.data

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class PrepareSetLicenseKeyDto(
  @field:NotBlank
  var licenseKey: String = "",

  @field:Min(1)
  var seats: Long = 0,
)
