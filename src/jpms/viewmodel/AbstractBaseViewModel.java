package jpms.viewmodel;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jpms.callback.MessageCallBack;
import jpms.messaging.ViewModelMessage;
import jpms.view.IBasicView;

/**
 *
 * @author m.elz
 */
public abstract class AbstractBaseViewModel {
        
    private  Connection connection;
    private Channel channel;
    
    public void showModualStage(Class<?> view, String stageKey, String windowTitle){
        IBasicView basicView = (IBasicView) jpms.JPMS.getInjector().getInstance(view);
        basicView.payloadBindings();
        
        Parent root = (Parent) basicView.getView();     
        
        //this is needed, because guice hold an instance of xyzView.class. so it had also one reference to a scene/stage.
        //A root can only have one refernce to a scene/stage!
        //we can reuse our view simple with the .show() method.
        if(root.getScene() != null){
            Stage dialogStage = jpms.JPMS.getStageMap().get(stageKey);
            dialogStage.show();
        }
        else{
            Scene scene = new Scene(root);
                        
            Stage dialogStage = new Stage();
            dialogStage.setTitle(windowTitle);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/images/icon.png")));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(jpms.JPMS.getPrimaryStage());

            jpms.JPMS.getStageMap().put(stageKey, dialogStage);

            dialogStage.setScene(scene);
            dialogStage.show();
        }
    }   
    
    protected void sendMessage(String queueName, String msg) throws IOException{
        setupMessageConnection();
        
        channel.basicPublish("", queueName, null, msg.getBytes());
        
        closeMessageConnection();
    }
    
    protected void receiveMessage(String queueName, final MessageCallBack callback) throws IOException, InterruptedException{
        setupMessageConnection();
        
        channel.queueDeclare(queueName, false, false, false, null);
        
        channel.basicConsume(queueName, false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTAg,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException
            {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                
                // (process the message components here ...)
                String msg = new String(body);
                
                if(msg.equals(ViewModelMessage.NEW_USER_ADDED.name())){
                    callback.execute(ViewModelMessage.NEW_USER_ADDED);
                }
                
                channel.basicAck(deliveryTag, false);
            }
        });
    }
    
    private void setupMessageConnection() throws IOException{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        connection = factory.newConnection();
        channel = connection.createChannel();
    }
    
    private void closeMessageConnection() throws IOException{
        channel.close();
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public Channel getChannel() {
        return channel;
    }

}
