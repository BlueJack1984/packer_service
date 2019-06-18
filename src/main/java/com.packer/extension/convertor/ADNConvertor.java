package com.packer.extension.convertor;

import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.packet.ConverterException;
import com.packer.commons.sms.packet.IValueConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ADN格式的字符串转换器.java
 * 
 * @description:
 * @version: 1.0.0
 * @modify:
 */
public class ADNConvertor implements IValueConverter {

	private static final long serialVersionUID = -6536192018300723660L;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public String convert(String value) throws ConverterException {

		try {
			String adnString = LFStringUtil.string2ADN(value);
			log.debug("原始字符串为：[" + value + "],转换后的字符串为：[" + adnString + "]");
			return adnString;
		} catch (Exception e) {
			throw new ConverterException(
					"转换成的ADN字符串出现异常，原始数据为:[" + value + "]", e);
		}
	}

	/**
	 * 将ADN字符串转换成原始字符串数据
	 */
	@Override
	public String reconvert(String value) throws ConverterException {
		try {
			String srcString = LFStringUtil.adn2string(value);
			log.debug("ADN格式的字符串为：[" + value + "],转换后的原始字符串为：[" + srcString
					+ "]");
			return srcString;
		} catch (Exception e) {
			throw new ConverterException("将ADN字符串转换成原始字符串出现异常，AND数据为:[" + value
					+ "]", e);
		}
	}

	@Override
	public String getName() {
		return "扩展的ADN转换器";
	}

	@Override
	public String getDescription() {
		return "扩展器";
	}

}
