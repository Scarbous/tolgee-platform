package io.tolgee.component.machineTranslation.providers

import io.tolgee.component.machineTranslation.MtValueProvider
import io.tolgee.configuration.tolgee.machineTranslation.AwsMachineTranslationProperties
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.translate.TranslateClient
import software.amazon.awssdk.services.translate.model.TranslateTextRequest

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class AwsMtValueProvider(
  private val awsMachineTranslationProperties: AwsMachineTranslationProperties,
  private val amazonTranslate: TranslateClient?
) : AbstractMtValueProvider() {
  override val isEnabled: Boolean
    get() = awsMachineTranslationProperties.enabled
      ?: (awsMachineTranslationProperties.accessKey != null && awsMachineTranslationProperties.secretKey != null)

  override fun translateViaProvider(params: ProviderTranslateParams): MtValueProvider.MtResult {
    val result = translateService.translateText(
      TranslateTextRequest.builder()
        .sourceLanguageCode(params.sourceLanguageTag)
        .targetLanguageCode(params.targetLanguageTag)
        .text(params.text)
        .build()
    )
    return MtValueProvider.MtResult(
      result.translatedText(),
      params.text.length * 100
    )
  }

  private val translateService by lazy {
    amazonTranslate ?: throw IllegalStateException("AmazonTranslate is not injected")
  }

  override val supportedLanguages = arrayOf(
    "af",
    "sq",
    "am",
    "ar",
    "hy",
    "az",
    "bn",
    "bs",
    "bg",
    "ca",
    "zh",
    "zh-TW",
    "hr",
    "cs",
    "da ",
    "fa-AF",
    "nl ",
    "en",
    "et",
    "fa",
    "tl",
    "fi",
    "fr",
    "fr-CA",
    "ka",
    "de",
    "el",
    "gu",
    "ht",
    "ha",
    "he ",
    "hi",
    "hu",
    "is",
    "id ",
    "ga",
    "it",
    "ja",
    "kn",
    "kk",
    "ko",
    "lv",
    "lt",
    "mk",
    "ms",
    "ml",
    "mt",
    "mr",
    "mn",
    "no",
    "ps",
    "pl",
    "pt",
    "pt-PT",
    "pa",
    "ro",
    "ru",
    "sr",
    "si",
    "sk",
    "sl",
    "so",
    "es",
    "es-MX",
    "sw",
    "sv",
    "ta",
    "te",
    "th",
    "tr",
    "uk",
    "ur",
    "uz",
    "vi",
    "cy"
  )
}
