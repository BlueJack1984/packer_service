package com.packer.extension.valuesource;


import com.packer.commons.sms.lang.LFStringUtil;
import com.packer.commons.sms.packet.IValueSource;
import com.packer.commons.sms.packet.ValueSourceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 密钥索引值源
 *
 * @author Administrator
 *
 */
public class KeyIndexValueSource implements IValueSource {

    /**
     *
     */
    private static final long serialVersionUID = 6405005289315109003L;

    @Override
    public List<String> getValues() throws ValueSourceException {
        List<String> result = new ArrayList<String>();

        int i = new Random().nextInt(5) + 1;
        String keyIndex = LFStringUtil.paddingHeadZero(Integer.toString(i), 2);
        result.add(keyIndex);
        return result;
    }

    @Override
    public String getDescription() {

        return "密钥索引值源";
    }

    @Override
    public String getName() {
        return "密钥索引值源";
    }

}

