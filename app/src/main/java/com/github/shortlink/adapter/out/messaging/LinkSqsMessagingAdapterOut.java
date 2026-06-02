package com.github.shortlink.adapter.out.messaging;

import com.github.shortlink.core.domain.Link;
import com.github.shortlink.core.port.out.LinkMessagingOut;
import org.springframework.stereotype.Component;

@Component
public class LinkSqsMessagingAdapterOut implements LinkMessagingOut {
    @Override
    public void publishUpdateLinkCount(Link link) {

    }
}
