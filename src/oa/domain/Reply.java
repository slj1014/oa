package oa.domain;

import java.io.Serializable;

public class Reply extends Article implements Serializable {
private Topic topic;//所属的主题

public Topic getTopic() {
	return topic;
}

public void setTopic(Topic topic) {
	this.topic = topic;
}

}
