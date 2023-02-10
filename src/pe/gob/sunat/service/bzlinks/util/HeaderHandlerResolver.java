/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.gob.sunat.service.bzlinks.util;

import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

/**
 *
 * @author Cesar´s
 */
public class HeaderHandlerResolver implements HandlerResolver {

    private String vruc;

    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {

        List<Handler> handlerChain = new ArrayList<Handler>();
        HeaderHandler hh = new HeaderHandler();

        hh.setVruc(vruc);

        handlerChain.add(hh);
        return handlerChain;
    }

    public String getVruc() {
        return vruc;
    }

    public void setVruc(String vruc) {
        this.vruc = vruc;
    }
}
