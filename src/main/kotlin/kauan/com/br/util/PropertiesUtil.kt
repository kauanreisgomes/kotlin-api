package kauan.com.br.util

import com.typesafe.config.ConfigFactory
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object PropertiesUtil {
    private val config = ConfigFactory.load()
    val datetimePattern: String = config.getString("app.datetime-pattern")
}

fun getDateTimeFormatterDefault() = DateTimeFormatter.ofPattern(PropertiesUtil.datetimePattern)

fun getZoneOffSetDefault() = ZoneId.systemDefault().rules.getOffset(java.time.Instant.now())