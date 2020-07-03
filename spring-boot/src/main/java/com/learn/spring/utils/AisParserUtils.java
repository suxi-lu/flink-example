package com.learn.spring.utils;

import dk.dma.ais.binary.SixbitException;
import dk.dma.ais.message.AisMessage;
import dk.dma.ais.message.AisMessageException;
import dk.dma.ais.sentence.SentenceException;
import dk.dma.ais.sentence.Vdm;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> Title: 标题 </p>
 * <pre> Description: 描述 </pre>
 * date: 2020/6/29 21:52
 * <p>
 * Copyright: © 2012-2020 . All rights reserved.
 * Company:
 *
 * @author lu_it
 * @version V1.0
 * @Package com.learn.message.utils
 */
@Slf4j
public class AisParserUtils {

    public static AisMessage parse(String msg) {
        try {
            Vdm vdm = new Vdm();
            vdm.parse(msg);

            AisMessage aisMessage = AisMessage.getInstance(vdm);
            return aisMessage;
        } catch (SentenceException e) {
            log.error("ais parse exception: SentenceException");
        } catch (SixbitException e) {
            log.error("ais parse exception: SixbitException");
        } catch (AisMessageException e) {
            log.error("ais parse exception: AisMessageException");
        }
        return null;
    }

}
