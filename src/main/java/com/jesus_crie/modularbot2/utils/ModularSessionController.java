package com.jesus_crie.modularbot2.utils;

import net.dv8tion.jda.core.utils.SessionControllerAdapter;
import org.slf4j.LoggerFactory;

public class ModularSessionController extends SessionControllerAdapter {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger("Session Controller");

    @Override
    public void appendSession(SessionConnectNode node) {
        logger.info("Queuing new node !");
        super.appendSession(node);
    }
}
