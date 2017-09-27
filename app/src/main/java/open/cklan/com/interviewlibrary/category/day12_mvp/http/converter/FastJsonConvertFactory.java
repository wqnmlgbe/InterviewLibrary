/*
 * Copyright (c) 2016 ShenBianVip, Ltd.
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and
 *  confidential.
 * Created on 1/5/16 4:00 PM
 * ProjectName: shenbian_android_cloud_speaker ; ModuleName: CSpeakerPhone ; ClassName: FastJsonConvertFactory.
 * Author: Lena; Last Modified: 1/5/16 4:00 PM.
 *  This file is originally created by Lena.
 */

package open.cklan.com.interviewlibrary.category.day12_mvp.http.converter;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;


public class FastJsonConvertFactory extends Converter.Factory{

    public static FastJsonConvertFactory create() {
       return new FastJsonConvertFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConvert<>();
    }
}
