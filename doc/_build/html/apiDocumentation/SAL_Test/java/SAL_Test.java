


package org.lsst.sal;

import org.opensplice.dds.dcps.TypeSupportImpl;
import DDS.*;
import DDS.STATUS_MASK_NONE; 
import DDS.DOMAIN_ID_DEFAULT;
import DDS.DATAREADER_QOS_USE_TOPIC_QOS;
import DDS.DataReader;
import DDS.DataWriter;
import DDS.DataWriterQosHolder;
import DDS.DomainParticipant;
import DDS.DomainParticipantFactory;
import DDS.DurabilityQosPolicyKind;
import DDS.PARTICIPANT_QOS_DEFAULT;
import DDS.Publisher;
import DDS.PublisherQosHolder;
import DDS.ReliabilityQosPolicyKind;
import DDS.Subscriber;
import DDS.SubscriberQosHolder;
import DDS.Topic;
import DDS.TopicQosHolder;
import DDS.DurabilityQosPolicyKind;
import DDS.HANDLE_NIL;
import DDS.DataReader;
import DDS.LENGTH_UNLIMITED;
import DDS.SampleInfoSeqHolder;
import DDS.QosProvider;
import org.lsst.sal.salActor;
import org.lsst.sal.salUtils;
import java.util.Random;

import Test.*;
// INSERT SAL IMPORTS

public class SAL_Test {

	private DomainParticipantFactory dpf;
	private DomainParticipant participant;
	private Topic topic;
        private Topic topic2;
        private ContentFilteredTopic filteredtopic;
        private ContentFilteredTopic filteredtopic2;
	private TopicQosHolder topicQos = new TopicQosHolder();
	private TopicQosHolder topicQos2 = new TopicQosHolder();
	private PublisherQosHolder pubQos = new PublisherQosHolder();
	private SubscriberQosHolder subQos = new SubscriberQosHolder();

	private DataWriterQosHolder WQosH = new DataWriterQosHolder();

	private Publisher publisher;
	private DataWriter writer;
	private DataWriter writer2;

	private Subscriber subscriber;
	private DataReader reader;
	private DataReader reader2;
        private QosProvider commandQos;
        private QosProvider eventQos;
        private QosProvider telemetryQos;
        private QosProvider ackcmdQos;
	private String typeName;
	private String typeName2;
	private String partitionName;
        private Boolean hasReader;
        private Boolean hasWriter;
        private Boolean hasEventReader;
        private Boolean hasEventWriter;
        private Boolean hasProcessor;
        private Boolean hasCommand;
        private int debugLevel;
        private int sndSeqNum;
        private int rcvSeqNum;
        private int rcvOrigin;
        private int historySync;
        private String rcvIdentity;
        private int subsystemID;
        private int origin;
        private int ddsIPaddress;
        private String domainName;
        private String partitionPrefix;
        private int lastActor_telemetry;
        private int lastActor_command;
        private int lastActor_event;
        private String CSC_identity;
 
        salUtils salUtil = new salUtils();
        salActor[] sal = new salActor[SAL__ACTORS_MAXCOUNT];

  public static final int SAL__Test_ackcmd_ACTOR = 0;
  public static final int SAL__Test_arrays_ACTOR = 1;
  public static final int SAL__Test_command_abort_ACTOR = 2;
  public static final int SAL__Test_command_disable_ACTOR = 3;
  public static final int SAL__Test_command_enable_ACTOR = 4;
  public static final int SAL__Test_command_enterControl_ACTOR = 5;
  public static final int SAL__Test_command_exitControl_ACTOR = 6;
  public static final int SAL__Test_command_fault_ACTOR = 7;
  public static final int SAL__Test_command_setArrays_ACTOR = 8;
  public static final int SAL__Test_command_setAuthList_ACTOR = 9;
  public static final int SAL__Test_command_setLogLevel_ACTOR = 10;
  public static final int SAL__Test_command_setScalars_ACTOR = 11;
  public static final int SAL__Test_command_setValue_ACTOR = 12;
  public static final int SAL__Test_command_standby_ACTOR = 13;
  public static final int SAL__Test_command_start_ACTOR = 14;
  public static final int SAL__Test_command_wait_ACTOR = 15;
  public static final int SAL__Test_logevent_appliedSettingsMatchStart_ACTOR = 16;
  public static final int SAL__Test_logevent_arrays_ACTOR = 17;
  public static final int SAL__Test_logevent_authList_ACTOR = 18;
  public static final int SAL__Test_logevent_errorCode_ACTOR = 19;
  public static final int SAL__Test_logevent_heartbeat_ACTOR = 20;
  public static final int SAL__Test_logevent_logLevel_ACTOR = 21;
  public static final int SAL__Test_logevent_logMessage_ACTOR = 22;
  public static final int SAL__Test_logevent_scalars_ACTOR = 23;
  public static final int SAL__Test_logevent_settingVersions_ACTOR = 24;
  public static final int SAL__Test_logevent_settingsApplied_ACTOR = 25;
  public static final int SAL__Test_logevent_simulationMode_ACTOR = 26;
  public static final int SAL__Test_logevent_softwareVersions_ACTOR = 27;
  public static final int SAL__Test_logevent_summaryState_ACTOR = 28;
  public static final int SAL__Test_scalars_ACTOR = 29;
 public static final int SAL__ACTORS_MAXCOUNT = 30;

  public void initSalActors ()
  {
     String pname;
     int status=-1;

    sal[0]=new salActor();
    sal[0].topicHandle="Test_ackcmd_902e1de4";
    sal[0].topicName="Test_ackcmd";
    status = ackcmdQos.get_topic_qos(sal[0].topicQos, null);
    status = ackcmdQos.get_datareader_qos(sal[0].RQosH, null);
    status = ackcmdQos.get_datawriter_qos(sal[0].WQosH, null);
    status = ackcmdQos.get_publisher_qos(sal[0].pubQos, null);
    status = ackcmdQos.get_subscriber_qos(sal[0].subQos, null);
    status = ackcmdQos.get_topic_qos(sal[0].topicQos2, null);

      pname = partitionPrefix + ".Test.data";
      sal[0].partition = pname;

    sal[1]=new salActor();
    sal[1].topicHandle="Test_arrays_f7d0e190";
    sal[1].topicName="Test_arrays";
    status = telemetryQos.get_topic_qos(sal[1].topicQos, null);
    status = telemetryQos.get_datareader_qos(sal[1].RQosH, null);
    status = telemetryQos.get_datawriter_qos(sal[1].WQosH, null);
    status = telemetryQos.get_publisher_qos(sal[1].pubQos, null);
    status = telemetryQos.get_subscriber_qos(sal[1].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[1].partition = pname;

    sal[2]=new salActor();
    sal[2].topicHandle="Test_command_abort_1c6758a1";
    sal[2].topicName="Test_command_abort";
    status = commandQos.get_topic_qos(sal[2].topicQos, null);
    status = commandQos.get_datareader_qos(sal[2].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[2].WQosH, null);
    status = commandQos.get_publisher_qos(sal[2].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[2].subQos, null);
    status = commandQos.get_topic_qos(sal[2].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[2].partition = pname;

    sal[3]=new salActor();
    sal[3].topicHandle="Test_command_disable_79c48b19";
    sal[3].topicName="Test_command_disable";
    status = commandQos.get_topic_qos(sal[3].topicQos, null);
    status = commandQos.get_datareader_qos(sal[3].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[3].WQosH, null);
    status = commandQos.get_publisher_qos(sal[3].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[3].subQos, null);
    status = commandQos.get_topic_qos(sal[3].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[3].partition = pname;

    sal[4]=new salActor();
    sal[4].topicHandle="Test_command_enable_69193c6f";
    sal[4].topicName="Test_command_enable";
    status = commandQos.get_topic_qos(sal[4].topicQos, null);
    status = commandQos.get_datareader_qos(sal[4].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[4].WQosH, null);
    status = commandQos.get_publisher_qos(sal[4].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[4].subQos, null);
    status = commandQos.get_topic_qos(sal[4].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[4].partition = pname;

    sal[5]=new salActor();
    sal[5].topicHandle="Test_command_enterControl_abb0d976";
    sal[5].topicName="Test_command_enterControl";
    status = commandQos.get_topic_qos(sal[5].topicQos, null);
    status = commandQos.get_datareader_qos(sal[5].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[5].WQosH, null);
    status = commandQos.get_publisher_qos(sal[5].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[5].subQos, null);
    status = commandQos.get_topic_qos(sal[5].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[5].partition = pname;

    sal[6]=new salActor();
    sal[6].topicHandle="Test_command_exitControl_9b992131";
    sal[6].topicName="Test_command_exitControl";
    status = commandQos.get_topic_qos(sal[6].topicQos, null);
    status = commandQos.get_datareader_qos(sal[6].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[6].WQosH, null);
    status = commandQos.get_publisher_qos(sal[6].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[6].subQos, null);
    status = commandQos.get_topic_qos(sal[6].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[6].partition = pname;

    sal[7]=new salActor();
    sal[7].topicHandle="Test_command_fault_4b850e28";
    sal[7].topicName="Test_command_fault";
    status = commandQos.get_topic_qos(sal[7].topicQos, null);
    status = commandQos.get_datareader_qos(sal[7].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[7].WQosH, null);
    status = commandQos.get_publisher_qos(sal[7].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[7].subQos, null);
    status = commandQos.get_topic_qos(sal[7].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[7].partition = pname;

    sal[8]=new salActor();
    sal[8].topicHandle="Test_command_setArrays_e11a567a";
    sal[8].topicName="Test_command_setArrays";
    status = commandQos.get_topic_qos(sal[8].topicQos, null);
    status = commandQos.get_datareader_qos(sal[8].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[8].WQosH, null);
    status = commandQos.get_publisher_qos(sal[8].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[8].subQos, null);
    status = commandQos.get_topic_qos(sal[8].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[8].partition = pname;

    sal[9]=new salActor();
    sal[9].topicHandle="Test_command_setAuthList_a648281d";
    sal[9].topicName="Test_command_setAuthList";
    status = commandQos.get_topic_qos(sal[9].topicQos, null);
    status = commandQos.get_datareader_qos(sal[9].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[9].WQosH, null);
    status = commandQos.get_publisher_qos(sal[9].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[9].subQos, null);
    status = commandQos.get_topic_qos(sal[9].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[9].partition = pname;

    sal[10]=new salActor();
    sal[10].topicHandle="Test_command_setLogLevel_48c8505d";
    sal[10].topicName="Test_command_setLogLevel";
    status = commandQos.get_topic_qos(sal[10].topicQos, null);
    status = commandQos.get_datareader_qos(sal[10].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[10].WQosH, null);
    status = commandQos.get_publisher_qos(sal[10].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[10].subQos, null);
    status = commandQos.get_topic_qos(sal[10].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[10].partition = pname;

    sal[11]=new salActor();
    sal[11].topicHandle="Test_command_setScalars_0fcc702b";
    sal[11].topicName="Test_command_setScalars";
    status = commandQos.get_topic_qos(sal[11].topicQos, null);
    status = commandQos.get_datareader_qos(sal[11].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[11].WQosH, null);
    status = commandQos.get_publisher_qos(sal[11].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[11].subQos, null);
    status = commandQos.get_topic_qos(sal[11].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[11].partition = pname;

    sal[12]=new salActor();
    sal[12].topicHandle="Test_command_setValue_caab4364";
    sal[12].topicName="Test_command_setValue";
    status = commandQos.get_topic_qos(sal[12].topicQos, null);
    status = commandQos.get_datareader_qos(sal[12].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[12].WQosH, null);
    status = commandQos.get_publisher_qos(sal[12].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[12].subQos, null);
    status = commandQos.get_topic_qos(sal[12].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[12].partition = pname;

    sal[13]=new salActor();
    sal[13].topicHandle="Test_command_standby_7054c900";
    sal[13].topicName="Test_command_standby";
    status = commandQos.get_topic_qos(sal[13].topicQos, null);
    status = commandQos.get_datareader_qos(sal[13].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[13].WQosH, null);
    status = commandQos.get_publisher_qos(sal[13].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[13].subQos, null);
    status = commandQos.get_topic_qos(sal[13].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[13].partition = pname;

    sal[14]=new salActor();
    sal[14].topicHandle="Test_command_start_74351d61";
    sal[14].topicName="Test_command_start";
    status = commandQos.get_topic_qos(sal[14].topicQos, null);
    status = commandQos.get_datareader_qos(sal[14].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[14].WQosH, null);
    status = commandQos.get_publisher_qos(sal[14].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[14].subQos, null);
    status = commandQos.get_topic_qos(sal[14].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[14].partition = pname;

    sal[15]=new salActor();
    sal[15].topicHandle="Test_command_wait_1b2e8a6f";
    sal[15].topicName="Test_command_wait";
    status = commandQos.get_topic_qos(sal[15].topicQos, null);
    status = commandQos.get_datareader_qos(sal[15].RQosH, null);
    status = commandQos.get_datawriter_qos(sal[15].WQosH, null);
    status = commandQos.get_publisher_qos(sal[15].pubQos, null);
    status = commandQos.get_subscriber_qos(sal[15].subQos, null);
    status = commandQos.get_topic_qos(sal[15].topicQos2, null);

      pname = partitionPrefix + ".Test.cmd";
      sal[15].partition = pname;

    sal[16]=new salActor();
    sal[16].topicHandle="Test_logevent_appliedSettingsMatchStart_77f17e02";
    sal[16].topicName="Test_logevent_appliedSettingsMatchStart";
    status = eventQos.get_topic_qos(sal[16].topicQos, null);
    status = eventQos.get_datareader_qos(sal[16].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[16].WQosH, null);
    status = eventQos.get_publisher_qos(sal[16].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[16].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[16].partition = pname;

    sal[17]=new salActor();
    sal[17].topicHandle="Test_logevent_arrays_5720d768";
    sal[17].topicName="Test_logevent_arrays";
    status = eventQos.get_topic_qos(sal[17].topicQos, null);
    status = eventQos.get_datareader_qos(sal[17].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[17].WQosH, null);
    status = eventQos.get_publisher_qos(sal[17].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[17].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[17].partition = pname;

    sal[18]=new salActor();
    sal[18].topicHandle="Test_logevent_authList_3447a91e";
    sal[18].topicName="Test_logevent_authList";
    status = eventQos.get_topic_qos(sal[18].topicQos, null);
    status = eventQos.get_datareader_qos(sal[18].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[18].WQosH, null);
    status = eventQos.get_publisher_qos(sal[18].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[18].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[18].partition = pname;

    sal[19]=new salActor();
    sal[19].topicHandle="Test_logevent_errorCode_12906fff";
    sal[19].topicName="Test_logevent_errorCode";
    status = eventQos.get_topic_qos(sal[19].topicQos, null);
    status = eventQos.get_datareader_qos(sal[19].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[19].WQosH, null);
    status = eventQos.get_publisher_qos(sal[19].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[19].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[19].partition = pname;

    sal[20]=new salActor();
    sal[20].topicHandle="Test_logevent_heartbeat_57130bcb";
    sal[20].topicName="Test_logevent_heartbeat";
    status = eventQos.get_topic_qos(sal[20].topicQos, null);
    status = eventQos.get_datareader_qos(sal[20].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[20].WQosH, null);
    status = eventQos.get_publisher_qos(sal[20].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[20].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[20].partition = pname;

    sal[21]=new salActor();
    sal[21].topicHandle="Test_logevent_logLevel_7aeafb06";
    sal[21].topicName="Test_logevent_logLevel";
    status = eventQos.get_topic_qos(sal[21].topicQos, null);
    status = eventQos.get_datareader_qos(sal[21].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[21].WQosH, null);
    status = eventQos.get_publisher_qos(sal[21].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[21].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[21].partition = pname;

    sal[22]=new salActor();
    sal[22].topicHandle="Test_logevent_logMessage_f4568bfc";
    sal[22].topicName="Test_logevent_logMessage";
    status = eventQos.get_topic_qos(sal[22].topicQos, null);
    status = eventQos.get_datareader_qos(sal[22].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[22].WQosH, null);
    status = eventQos.get_publisher_qos(sal[22].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[22].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[22].partition = pname;

    sal[23]=new salActor();
    sal[23].topicHandle="Test_logevent_scalars_1d41189e";
    sal[23].topicName="Test_logevent_scalars";
    status = eventQos.get_topic_qos(sal[23].topicQos, null);
    status = eventQos.get_datareader_qos(sal[23].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[23].WQosH, null);
    status = eventQos.get_publisher_qos(sal[23].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[23].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[23].partition = pname;

    sal[24]=new salActor();
    sal[24].topicHandle="Test_logevent_settingVersions_11e4d332";
    sal[24].topicName="Test_logevent_settingVersions";
    status = eventQos.get_topic_qos(sal[24].topicQos, null);
    status = eventQos.get_datareader_qos(sal[24].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[24].WQosH, null);
    status = eventQos.get_publisher_qos(sal[24].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[24].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[24].partition = pname;

    sal[25]=new salActor();
    sal[25].topicHandle="Test_logevent_settingsApplied_02b3cc9e";
    sal[25].topicName="Test_logevent_settingsApplied";
    status = eventQos.get_topic_qos(sal[25].topicQos, null);
    status = eventQos.get_datareader_qos(sal[25].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[25].WQosH, null);
    status = eventQos.get_publisher_qos(sal[25].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[25].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[25].partition = pname;

    sal[26]=new salActor();
    sal[26].topicHandle="Test_logevent_simulationMode_fcccc7ae";
    sal[26].topicName="Test_logevent_simulationMode";
    status = eventQos.get_topic_qos(sal[26].topicQos, null);
    status = eventQos.get_datareader_qos(sal[26].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[26].WQosH, null);
    status = eventQos.get_publisher_qos(sal[26].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[26].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[26].partition = pname;

    sal[27]=new salActor();
    sal[27].topicHandle="Test_logevent_softwareVersions_1f4be1b2";
    sal[27].topicName="Test_logevent_softwareVersions";
    status = eventQos.get_topic_qos(sal[27].topicQos, null);
    status = eventQos.get_datareader_qos(sal[27].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[27].WQosH, null);
    status = eventQos.get_publisher_qos(sal[27].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[27].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[27].partition = pname;

    sal[28]=new salActor();
    sal[28].topicHandle="Test_logevent_summaryState_7351a054";
    sal[28].topicName="Test_logevent_summaryState";
    status = eventQos.get_topic_qos(sal[28].topicQos, null);
    status = eventQos.get_datareader_qos(sal[28].RQosH, null);
    status = eventQos.get_datawriter_qos(sal[28].WQosH, null);
    status = eventQos.get_publisher_qos(sal[28].pubQos, null);
    status = eventQos.get_subscriber_qos(sal[28].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[28].partition = pname;

    sal[29]=new salActor();
    sal[29].topicHandle="Test_scalars_e4001923";
    sal[29].topicName="Test_scalars";
    status = telemetryQos.get_topic_qos(sal[29].topicQos, null);
    status = telemetryQos.get_datareader_qos(sal[29].RQosH, null);
    status = telemetryQos.get_datawriter_qos(sal[29].WQosH, null);
    status = telemetryQos.get_publisher_qos(sal[29].pubQos, null);
    status = telemetryQos.get_subscriber_qos(sal[29].subQos, null);

      pname = partitionPrefix + ".Test.data";
      sal[29].partition = pname;


  }

/// Returns the current SAL version e.g. "4.1.0"
public String getSALVersion()
{
    return "6.0.0";
}

/// Returns the current XML version e.g. "5.0.0"
public String getXMLVersion()
{
    return "9.0.0";
}

/// Returns the current OpenSpliceDDS version e.g. "6.9.181127OSS"
public String getOSPLVersion()
{
    String osplver = System.getenv("OSPL_RELEASE");
    return osplver;
}


/** Configure DDS type support for Test DDS topics. 
  * @param topicName The DDS topic name
  */
        public int salTypeSupport(String topicName) {
    String[] parts = topicName.split("_");
                if ("Test".equals(parts[0]) ) {

                    if ( "Test_arrays".equals(topicName) ) {
      arrays_f7d0e190TypeSupport arrays_f7d0e190TS = new arrays_f7d0e190TypeSupport();
      registerType(arrays_f7d0e190TS);
                        return SAL__OK;
        }

                    if ( "Test_command_abort".equals(topicName) ) {
      command_abort_1c6758a1TypeSupport command_abort_1c6758a1TS = new command_abort_1c6758a1TypeSupport();
      registerType(command_abort_1c6758a1TS);
                        return SAL__OK;
        }

                    if ( "Test_command_disable".equals(topicName) ) {
      command_disable_79c48b19TypeSupport command_disable_79c48b19TS = new command_disable_79c48b19TypeSupport();
      registerType(command_disable_79c48b19TS);
                        return SAL__OK;
        }

                    if ( "Test_command_enable".equals(topicName) ) {
      command_enable_69193c6fTypeSupport command_enable_69193c6fTS = new command_enable_69193c6fTypeSupport();
      registerType(command_enable_69193c6fTS);
                        return SAL__OK;
        }

                    if ( "Test_command_enterControl".equals(topicName) ) {
      command_enterControl_abb0d976TypeSupport command_enterControl_abb0d976TS = new command_enterControl_abb0d976TypeSupport();
      registerType(command_enterControl_abb0d976TS);
                        return SAL__OK;
        }

                    if ( "Test_command_exitControl".equals(topicName) ) {
      command_exitControl_9b992131TypeSupport command_exitControl_9b992131TS = new command_exitControl_9b992131TypeSupport();
      registerType(command_exitControl_9b992131TS);
                        return SAL__OK;
        }

                    if ( "Test_command_fault".equals(topicName) ) {
      command_fault_4b850e28TypeSupport command_fault_4b850e28TS = new command_fault_4b850e28TypeSupport();
      registerType(command_fault_4b850e28TS);
                        return SAL__OK;
        }

                    if ( "Test_command_setArrays".equals(topicName) ) {
      command_setArrays_e11a567aTypeSupport command_setArrays_e11a567aTS = new command_setArrays_e11a567aTypeSupport();
      registerType(command_setArrays_e11a567aTS);
                        return SAL__OK;
        }

                    if ( "Test_command_setAuthList".equals(topicName) ) {
      command_setAuthList_a648281dTypeSupport command_setAuthList_a648281dTS = new command_setAuthList_a648281dTypeSupport();
      registerType(command_setAuthList_a648281dTS);
                        return SAL__OK;
        }

                    if ( "Test_command_setLogLevel".equals(topicName) ) {
      command_setLogLevel_48c8505dTypeSupport command_setLogLevel_48c8505dTS = new command_setLogLevel_48c8505dTypeSupport();
      registerType(command_setLogLevel_48c8505dTS);
                        return SAL__OK;
        }

                    if ( "Test_command_setScalars".equals(topicName) ) {
      command_setScalars_0fcc702bTypeSupport command_setScalars_0fcc702bTS = new command_setScalars_0fcc702bTypeSupport();
      registerType(command_setScalars_0fcc702bTS);
                        return SAL__OK;
        }

                    if ( "Test_command_setValue".equals(topicName) ) {
      command_setValue_caab4364TypeSupport command_setValue_caab4364TS = new command_setValue_caab4364TypeSupport();
      registerType(command_setValue_caab4364TS);
                        return SAL__OK;
        }

                    if ( "Test_command_standby".equals(topicName) ) {
      command_standby_7054c900TypeSupport command_standby_7054c900TS = new command_standby_7054c900TypeSupport();
      registerType(command_standby_7054c900TS);
                        return SAL__OK;
        }

                    if ( "Test_command_start".equals(topicName) ) {
      command_start_74351d61TypeSupport command_start_74351d61TS = new command_start_74351d61TypeSupport();
      registerType(command_start_74351d61TS);
                        return SAL__OK;
        }

                    if ( "Test_command_wait".equals(topicName) ) {
      command_wait_1b2e8a6fTypeSupport command_wait_1b2e8a6fTS = new command_wait_1b2e8a6fTypeSupport();
      registerType(command_wait_1b2e8a6fTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_appliedSettingsMatchStart".equals(topicName) ) {
      logevent_appliedSettingsMatchStart_77f17e02TypeSupport logevent_appliedSettingsMatchStart_77f17e02TS = new logevent_appliedSettingsMatchStart_77f17e02TypeSupport();
      registerType(logevent_appliedSettingsMatchStart_77f17e02TS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_arrays".equals(topicName) ) {
      logevent_arrays_5720d768TypeSupport logevent_arrays_5720d768TS = new logevent_arrays_5720d768TypeSupport();
      registerType(logevent_arrays_5720d768TS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_authList".equals(topicName) ) {
      logevent_authList_3447a91eTypeSupport logevent_authList_3447a91eTS = new logevent_authList_3447a91eTypeSupport();
      registerType(logevent_authList_3447a91eTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_errorCode".equals(topicName) ) {
      logevent_errorCode_12906fffTypeSupport logevent_errorCode_12906fffTS = new logevent_errorCode_12906fffTypeSupport();
      registerType(logevent_errorCode_12906fffTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_heartbeat".equals(topicName) ) {
      logevent_heartbeat_57130bcbTypeSupport logevent_heartbeat_57130bcbTS = new logevent_heartbeat_57130bcbTypeSupport();
      registerType(logevent_heartbeat_57130bcbTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_logLevel".equals(topicName) ) {
      logevent_logLevel_7aeafb06TypeSupport logevent_logLevel_7aeafb06TS = new logevent_logLevel_7aeafb06TypeSupport();
      registerType(logevent_logLevel_7aeafb06TS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_logMessage".equals(topicName) ) {
      logevent_logMessage_f4568bfcTypeSupport logevent_logMessage_f4568bfcTS = new logevent_logMessage_f4568bfcTypeSupport();
      registerType(logevent_logMessage_f4568bfcTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_scalars".equals(topicName) ) {
      logevent_scalars_1d41189eTypeSupport logevent_scalars_1d41189eTS = new logevent_scalars_1d41189eTypeSupport();
      registerType(logevent_scalars_1d41189eTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_settingVersions".equals(topicName) ) {
      logevent_settingVersions_11e4d332TypeSupport logevent_settingVersions_11e4d332TS = new logevent_settingVersions_11e4d332TypeSupport();
      registerType(logevent_settingVersions_11e4d332TS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_settingsApplied".equals(topicName) ) {
      logevent_settingsApplied_02b3cc9eTypeSupport logevent_settingsApplied_02b3cc9eTS = new logevent_settingsApplied_02b3cc9eTypeSupport();
      registerType(logevent_settingsApplied_02b3cc9eTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_simulationMode".equals(topicName) ) {
      logevent_simulationMode_fcccc7aeTypeSupport logevent_simulationMode_fcccc7aeTS = new logevent_simulationMode_fcccc7aeTypeSupport();
      registerType(logevent_simulationMode_fcccc7aeTS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_softwareVersions".equals(topicName) ) {
      logevent_softwareVersions_1f4be1b2TypeSupport logevent_softwareVersions_1f4be1b2TS = new logevent_softwareVersions_1f4be1b2TypeSupport();
      registerType(logevent_softwareVersions_1f4be1b2TS);
                        return SAL__OK;
        }

                    if ( "Test_logevent_summaryState".equals(topicName) ) {
      logevent_summaryState_7351a054TypeSupport logevent_summaryState_7351a054TS = new logevent_summaryState_7351a054TypeSupport();
      registerType(logevent_summaryState_7351a054TS);
                        return SAL__OK;
        }

                    if ( "Test_scalars".equals(topicName) ) {
      scalars_e4001923TypeSupport scalars_e4001923TS = new scalars_e4001923TypeSupport();
      registerType(scalars_e4001923TS);
                        return SAL__OK;
        }

                    if ( "Test_ackcmd".equals(topicName) ) {
      ackcmd_902e1de4TypeSupport ackcmd_902e1de4TS = new ackcmd_902e1de4TypeSupport();
      registerType(ackcmd_902e1de4TS);
                        return SAL__OK;
        }
  }

  return SAL__ERR;
}
        public int salTypeSupport(int actorIdx) {

                    if ( actorIdx == SAL__Test_arrays_ACTOR ) {
      arrays_f7d0e190TypeSupport arrays_f7d0e190TS = new arrays_f7d0e190TypeSupport();
      registerType(actorIdx,arrays_f7d0e190TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_abort_ACTOR ) {
      command_abort_1c6758a1TypeSupport command_abort_1c6758a1TS = new command_abort_1c6758a1TypeSupport();
      registerType(actorIdx,command_abort_1c6758a1TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_disable_ACTOR ) {
      command_disable_79c48b19TypeSupport command_disable_79c48b19TS = new command_disable_79c48b19TypeSupport();
      registerType(actorIdx,command_disable_79c48b19TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_enable_ACTOR ) {
      command_enable_69193c6fTypeSupport command_enable_69193c6fTS = new command_enable_69193c6fTypeSupport();
      registerType(actorIdx,command_enable_69193c6fTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_enterControl_ACTOR ) {
      command_enterControl_abb0d976TypeSupport command_enterControl_abb0d976TS = new command_enterControl_abb0d976TypeSupport();
      registerType(actorIdx,command_enterControl_abb0d976TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_exitControl_ACTOR ) {
      command_exitControl_9b992131TypeSupport command_exitControl_9b992131TS = new command_exitControl_9b992131TypeSupport();
      registerType(actorIdx,command_exitControl_9b992131TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_fault_ACTOR ) {
      command_fault_4b850e28TypeSupport command_fault_4b850e28TS = new command_fault_4b850e28TypeSupport();
      registerType(actorIdx,command_fault_4b850e28TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_setArrays_ACTOR ) {
      command_setArrays_e11a567aTypeSupport command_setArrays_e11a567aTS = new command_setArrays_e11a567aTypeSupport();
      registerType(actorIdx,command_setArrays_e11a567aTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_setAuthList_ACTOR ) {
      command_setAuthList_a648281dTypeSupport command_setAuthList_a648281dTS = new command_setAuthList_a648281dTypeSupport();
      registerType(actorIdx,command_setAuthList_a648281dTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_setLogLevel_ACTOR ) {
      command_setLogLevel_48c8505dTypeSupport command_setLogLevel_48c8505dTS = new command_setLogLevel_48c8505dTypeSupport();
      registerType(actorIdx,command_setLogLevel_48c8505dTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_setScalars_ACTOR ) {
      command_setScalars_0fcc702bTypeSupport command_setScalars_0fcc702bTS = new command_setScalars_0fcc702bTypeSupport();
      registerType(actorIdx,command_setScalars_0fcc702bTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_setValue_ACTOR ) {
      command_setValue_caab4364TypeSupport command_setValue_caab4364TS = new command_setValue_caab4364TypeSupport();
      registerType(actorIdx,command_setValue_caab4364TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_standby_ACTOR ) {
      command_standby_7054c900TypeSupport command_standby_7054c900TS = new command_standby_7054c900TypeSupport();
      registerType(actorIdx,command_standby_7054c900TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_start_ACTOR ) {
      command_start_74351d61TypeSupport command_start_74351d61TS = new command_start_74351d61TypeSupport();
      registerType(actorIdx,command_start_74351d61TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_command_wait_ACTOR ) {
      command_wait_1b2e8a6fTypeSupport command_wait_1b2e8a6fTS = new command_wait_1b2e8a6fTypeSupport();
      registerType(actorIdx,command_wait_1b2e8a6fTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_appliedSettingsMatchStart_ACTOR ) {
      logevent_appliedSettingsMatchStart_77f17e02TypeSupport logevent_appliedSettingsMatchStart_77f17e02TS = new logevent_appliedSettingsMatchStart_77f17e02TypeSupport();
      registerType(actorIdx,logevent_appliedSettingsMatchStart_77f17e02TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_arrays_ACTOR ) {
      logevent_arrays_5720d768TypeSupport logevent_arrays_5720d768TS = new logevent_arrays_5720d768TypeSupport();
      registerType(actorIdx,logevent_arrays_5720d768TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_authList_ACTOR ) {
      logevent_authList_3447a91eTypeSupport logevent_authList_3447a91eTS = new logevent_authList_3447a91eTypeSupport();
      registerType(actorIdx,logevent_authList_3447a91eTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_errorCode_ACTOR ) {
      logevent_errorCode_12906fffTypeSupport logevent_errorCode_12906fffTS = new logevent_errorCode_12906fffTypeSupport();
      registerType(actorIdx,logevent_errorCode_12906fffTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_heartbeat_ACTOR ) {
      logevent_heartbeat_57130bcbTypeSupport logevent_heartbeat_57130bcbTS = new logevent_heartbeat_57130bcbTypeSupport();
      registerType(actorIdx,logevent_heartbeat_57130bcbTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_logLevel_ACTOR ) {
      logevent_logLevel_7aeafb06TypeSupport logevent_logLevel_7aeafb06TS = new logevent_logLevel_7aeafb06TypeSupport();
      registerType(actorIdx,logevent_logLevel_7aeafb06TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_logMessage_ACTOR ) {
      logevent_logMessage_f4568bfcTypeSupport logevent_logMessage_f4568bfcTS = new logevent_logMessage_f4568bfcTypeSupport();
      registerType(actorIdx,logevent_logMessage_f4568bfcTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_scalars_ACTOR ) {
      logevent_scalars_1d41189eTypeSupport logevent_scalars_1d41189eTS = new logevent_scalars_1d41189eTypeSupport();
      registerType(actorIdx,logevent_scalars_1d41189eTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_settingVersions_ACTOR ) {
      logevent_settingVersions_11e4d332TypeSupport logevent_settingVersions_11e4d332TS = new logevent_settingVersions_11e4d332TypeSupport();
      registerType(actorIdx,logevent_settingVersions_11e4d332TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_settingsApplied_ACTOR ) {
      logevent_settingsApplied_02b3cc9eTypeSupport logevent_settingsApplied_02b3cc9eTS = new logevent_settingsApplied_02b3cc9eTypeSupport();
      registerType(actorIdx,logevent_settingsApplied_02b3cc9eTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_simulationMode_ACTOR ) {
      logevent_simulationMode_fcccc7aeTypeSupport logevent_simulationMode_fcccc7aeTS = new logevent_simulationMode_fcccc7aeTypeSupport();
      registerType(actorIdx,logevent_simulationMode_fcccc7aeTS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_softwareVersions_ACTOR ) {
      logevent_softwareVersions_1f4be1b2TypeSupport logevent_softwareVersions_1f4be1b2TS = new logevent_softwareVersions_1f4be1b2TypeSupport();
      registerType(actorIdx,logevent_softwareVersions_1f4be1b2TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_logevent_summaryState_ACTOR ) {
      logevent_summaryState_7351a054TypeSupport logevent_summaryState_7351a054TS = new logevent_summaryState_7351a054TypeSupport();
      registerType(actorIdx,logevent_summaryState_7351a054TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_scalars_ACTOR ) {
      scalars_e4001923TypeSupport scalars_e4001923TS = new scalars_e4001923TypeSupport();
      registerType(actorIdx,scalars_e4001923TS);
                        return SAL__OK;
        }

                    if ( actorIdx == SAL__Test_ackcmd_ACTOR ) {
      ackcmd_902e1de4TypeSupport ackcmd_902e1de4TS = new ackcmd_902e1de4TypeSupport();
      registerType(actorIdx,ackcmd_902e1de4TS);
                        return SAL__OK;
        }

  return SAL__ERR;
}

/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_arrays</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.arrays data)
  {
          int status = SAL__OK;
          arrays_f7d0e190 SALInstance = new arrays_f7d0e190();
    int actorIdx = SAL__Test_arrays_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    arrays_f7d0e190DataWriter SALWriter = arrays_f7d0e190DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "f7d0e190";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample arrays] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           System.arraycopy(data.boolean0,0,SALInstance.boolean0,0,5);
           System.arraycopy(data.byte0,0,SALInstance.byte0,0,5);
           System.arraycopy(data.short0,0,SALInstance.short0,0,5);
           System.arraycopy(data.int0,0,SALInstance.int0,0,5);
           System.arraycopy(data.long0,0,SALInstance.long0,0,5);
           System.arraycopy(data.longLong0,0,SALInstance.longLong0,0,5);
           System.arraycopy(data.octet0,0,SALInstance.octet0,0,5);
           System.arraycopy(data.unsignedShort0,0,SALInstance.unsignedShort0,0,5);
           System.arraycopy(data.unsignedInt0,0,SALInstance.unsignedInt0,0,5);
           System.arraycopy(data.unsignedLong0,0,SALInstance.unsignedLong0,0,5);
           System.arraycopy(data.float0,0,SALInstance.float0,0,5);
           System.arraycopy(data.double0,0,SALInstance.double0,0,5);

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "arrays_f7d0e190DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_arrays</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.arrays data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          arrays_f7d0e190SeqHolder SALInstance = new arrays_f7d0e190SeqHolder();
    int actorIdx = SAL__Test_arrays_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  arrays_f7d0e190DataReader SALReader = arrays_f7d0e190DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample arrays ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           System.arraycopy(SALInstance.value[j].boolean0,0,data.boolean0,0,5);
           System.arraycopy(SALInstance.value[j].byte0,0,data.byte0,0,5);
           System.arraycopy(SALInstance.value[j].short0,0,data.short0,0,5);
           System.arraycopy(SALInstance.value[j].int0,0,data.int0,0,5);
           System.arraycopy(SALInstance.value[j].long0,0,data.long0,0,5);
           System.arraycopy(SALInstance.value[j].longLong0,0,data.longLong0,0,5);
           System.arraycopy(SALInstance.value[j].octet0,0,data.octet0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedShort0,0,data.unsignedShort0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedInt0,0,data.unsignedInt0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedLong0,0,data.unsignedLong0,0,5);
           System.arraycopy(SALInstance.value[j].float0,0,data.float0,0,5);
           System.arraycopy(SALInstance.value[j].double0,0,data.double0,0,5);
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_arrays</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_arrays
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.arrays data)
  {
    int status = -1;
    int actorIdx = SAL__Test_arrays_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_arrays or getNextSample_arrays
  */
  public int flushSamples(Test.arrays data)
  {
          int status = -1;
    int actorIdx = SAL__Test_arrays_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#abort>Test_command_abort</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_abort data)
  {
          int status = SAL__OK;
          command_abort_1c6758a1 SALInstance = new command_abort_1c6758a1();
    int actorIdx = SAL__Test_command_abort_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_abort_1c6758a1DataWriter SALWriter = command_abort_1c6758a1DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "1c6758a1";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_abort] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_abort_1c6758a1DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#abort>Test_command_abort</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_abort data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_abort_1c6758a1SeqHolder SALInstance = new command_abort_1c6758a1SeqHolder();
    int actorIdx = SAL__Test_command_abort_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_abort_1c6758a1DataReader SALReader = command_abort_1c6758a1DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_abort ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#abort>Test_command_abort</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_abort
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_abort data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_abort_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_abort or getNextSample_command_abort
  */
  public int flushSamples(Test.command_abort data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_abort_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#disable>Test_command_disable</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_disable data)
  {
          int status = SAL__OK;
          command_disable_79c48b19 SALInstance = new command_disable_79c48b19();
    int actorIdx = SAL__Test_command_disable_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_disable_79c48b19DataWriter SALWriter = command_disable_79c48b19DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "79c48b19";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_disable] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_disable_79c48b19DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#disable>Test_command_disable</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_disable data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_disable_79c48b19SeqHolder SALInstance = new command_disable_79c48b19SeqHolder();
    int actorIdx = SAL__Test_command_disable_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_disable_79c48b19DataReader SALReader = command_disable_79c48b19DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_disable ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#disable>Test_command_disable</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_disable
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_disable data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_disable_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_disable or getNextSample_command_disable
  */
  public int flushSamples(Test.command_disable data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_disable_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#enable>Test_command_enable</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_enable data)
  {
          int status = SAL__OK;
          command_enable_69193c6f SALInstance = new command_enable_69193c6f();
    int actorIdx = SAL__Test_command_enable_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_enable_69193c6fDataWriter SALWriter = command_enable_69193c6fDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "69193c6f";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_enable] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_enable_69193c6fDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#enable>Test_command_enable</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_enable data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_enable_69193c6fSeqHolder SALInstance = new command_enable_69193c6fSeqHolder();
    int actorIdx = SAL__Test_command_enable_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_enable_69193c6fDataReader SALReader = command_enable_69193c6fDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_enable ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#enable>Test_command_enable</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_enable
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_enable data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_enable_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_enable or getNextSample_command_enable
  */
  public int flushSamples(Test.command_enable data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_enable_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#entercontrol>Test_command_enterControl</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_enterControl data)
  {
          int status = SAL__OK;
          command_enterControl_abb0d976 SALInstance = new command_enterControl_abb0d976();
    int actorIdx = SAL__Test_command_enterControl_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_enterControl_abb0d976DataWriter SALWriter = command_enterControl_abb0d976DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "abb0d976";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_enterControl] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_enterControl_abb0d976DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#entercontrol>Test_command_enterControl</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_enterControl data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_enterControl_abb0d976SeqHolder SALInstance = new command_enterControl_abb0d976SeqHolder();
    int actorIdx = SAL__Test_command_enterControl_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_enterControl_abb0d976DataReader SALReader = command_enterControl_abb0d976DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_enterControl ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#entercontrol>Test_command_enterControl</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_enterControl
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_enterControl data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_enterControl_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_enterControl or getNextSample_command_enterControl
  */
  public int flushSamples(Test.command_enterControl data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_enterControl_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#exitcontrol>Test_command_exitControl</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_exitControl data)
  {
          int status = SAL__OK;
          command_exitControl_9b992131 SALInstance = new command_exitControl_9b992131();
    int actorIdx = SAL__Test_command_exitControl_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_exitControl_9b992131DataWriter SALWriter = command_exitControl_9b992131DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "9b992131";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_exitControl] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_exitControl_9b992131DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#exitcontrol>Test_command_exitControl</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_exitControl data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_exitControl_9b992131SeqHolder SALInstance = new command_exitControl_9b992131SeqHolder();
    int actorIdx = SAL__Test_command_exitControl_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_exitControl_9b992131DataReader SALReader = command_exitControl_9b992131DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_exitControl ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#exitcontrol>Test_command_exitControl</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_exitControl
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_exitControl data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_exitControl_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_exitControl or getNextSample_command_exitControl
  */
  public int flushSamples(Test.command_exitControl data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_exitControl_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#fault>Test_command_fault</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_fault data)
  {
          int status = SAL__OK;
          command_fault_4b850e28 SALInstance = new command_fault_4b850e28();
    int actorIdx = SAL__Test_command_fault_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_fault_4b850e28DataWriter SALWriter = command_fault_4b850e28DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "4b850e28";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_fault] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_fault_4b850e28DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#fault>Test_command_fault</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_fault data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_fault_4b850e28SeqHolder SALInstance = new command_fault_4b850e28SeqHolder();
    int actorIdx = SAL__Test_command_fault_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_fault_4b850e28DataReader SALReader = command_fault_4b850e28DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_fault ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#fault>Test_command_fault</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_fault
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_fault data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_fault_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_fault or getNextSample_command_fault
  */
  public int flushSamples(Test.command_fault data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_fault_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setarrays>Test_command_setArrays</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_setArrays data)
  {
          int status = SAL__OK;
          command_setArrays_e11a567a SALInstance = new command_setArrays_e11a567a();
    int actorIdx = SAL__Test_command_setArrays_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_setArrays_e11a567aDataWriter SALWriter = command_setArrays_e11a567aDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "e11a567a";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_setArrays] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
          System.arraycopy(data.boolean0,0,SALInstance.boolean0,0,5);
          System.arraycopy(data.byte0,0,SALInstance.byte0,0,5);
          System.arraycopy(data.short0,0,SALInstance.short0,0,5);
          System.arraycopy(data.int0,0,SALInstance.int0,0,5);
          System.arraycopy(data.long0,0,SALInstance.long0,0,5);
          System.arraycopy(data.longLong0,0,SALInstance.longLong0,0,5);
          System.arraycopy(data.octet0,0,SALInstance.octet0,0,5);
          System.arraycopy(data.unsignedShort0,0,SALInstance.unsignedShort0,0,5);
          System.arraycopy(data.unsignedInt0,0,SALInstance.unsignedInt0,0,5);
          System.arraycopy(data.unsignedLong0,0,SALInstance.unsignedLong0,0,5);
          System.arraycopy(data.float0,0,SALInstance.float0,0,5);
          System.arraycopy(data.double0,0,SALInstance.double0,0,5);

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_setArrays_e11a567aDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setarrays>Test_command_setArrays</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_setArrays data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_setArrays_e11a567aSeqHolder SALInstance = new command_setArrays_e11a567aSeqHolder();
    int actorIdx = SAL__Test_command_setArrays_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_setArrays_e11a567aDataReader SALReader = command_setArrays_e11a567aDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_setArrays ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           System.arraycopy(SALInstance.value[j].boolean0,0,data.boolean0,0,5);
           System.arraycopy(SALInstance.value[j].byte0,0,data.byte0,0,5);
           System.arraycopy(SALInstance.value[j].short0,0,data.short0,0,5);
           System.arraycopy(SALInstance.value[j].int0,0,data.int0,0,5);
           System.arraycopy(SALInstance.value[j].long0,0,data.long0,0,5);
           System.arraycopy(SALInstance.value[j].longLong0,0,data.longLong0,0,5);
           System.arraycopy(SALInstance.value[j].octet0,0,data.octet0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedShort0,0,data.unsignedShort0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedInt0,0,data.unsignedInt0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedLong0,0,data.unsignedLong0,0,5);
           System.arraycopy(SALInstance.value[j].float0,0,data.float0,0,5);
           System.arraycopy(SALInstance.value[j].double0,0,data.double0,0,5);
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setarrays>Test_command_setArrays</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_setArrays
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_setArrays data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_setArrays_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_setArrays or getNextSample_command_setArrays
  */
  public int flushSamples(Test.command_setArrays data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_setArrays_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setauthlist>Test_command_setAuthList</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_setAuthList data)
  {
          int status = SAL__OK;
          command_setAuthList_a648281d SALInstance = new command_setAuthList_a648281d();
    int actorIdx = SAL__Test_command_setAuthList_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_setAuthList_a648281dDataWriter SALWriter = command_setAuthList_a648281dDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "a648281d";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_setAuthList] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
          SALInstance.authorizedUsers = data.authorizedUsers;
          SALInstance.nonAuthorizedCSCs = data.nonAuthorizedCSCs;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_setAuthList_a648281dDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setauthlist>Test_command_setAuthList</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_setAuthList data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_setAuthList_a648281dSeqHolder SALInstance = new command_setAuthList_a648281dSeqHolder();
    int actorIdx = SAL__Test_command_setAuthList_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_setAuthList_a648281dDataReader SALReader = command_setAuthList_a648281dDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_setAuthList ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.authorizedUsers = SALInstance.value[j].authorizedUsers;
           data.nonAuthorizedCSCs = SALInstance.value[j].nonAuthorizedCSCs;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setauthlist>Test_command_setAuthList</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_setAuthList
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_setAuthList data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_setAuthList_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_setAuthList or getNextSample_command_setAuthList
  */
  public int flushSamples(Test.command_setAuthList data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_setAuthList_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setloglevel>Test_command_setLogLevel</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_setLogLevel data)
  {
          int status = SAL__OK;
          command_setLogLevel_48c8505d SALInstance = new command_setLogLevel_48c8505d();
    int actorIdx = SAL__Test_command_setLogLevel_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_setLogLevel_48c8505dDataWriter SALWriter = command_setLogLevel_48c8505dDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "48c8505d";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_setLogLevel] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
          SALInstance.level = data.level;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_setLogLevel_48c8505dDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setloglevel>Test_command_setLogLevel</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_setLogLevel data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_setLogLevel_48c8505dSeqHolder SALInstance = new command_setLogLevel_48c8505dSeqHolder();
    int actorIdx = SAL__Test_command_setLogLevel_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_setLogLevel_48c8505dDataReader SALReader = command_setLogLevel_48c8505dDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_setLogLevel ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.level = SALInstance.value[j].level;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setloglevel>Test_command_setLogLevel</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_setLogLevel
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_setLogLevel data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_setLogLevel_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_setLogLevel or getNextSample_command_setLogLevel
  */
  public int flushSamples(Test.command_setLogLevel data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_setLogLevel_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setscalars>Test_command_setScalars</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_setScalars data)
  {
          int status = SAL__OK;
          command_setScalars_0fcc702b SALInstance = new command_setScalars_0fcc702b();
    int actorIdx = SAL__Test_command_setScalars_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_setScalars_0fcc702bDataWriter SALWriter = command_setScalars_0fcc702bDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "0fcc702b";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_setScalars] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
          SALInstance.boolean0 = data.boolean0;
          SALInstance.byte0 = data.byte0;
          SALInstance.char0 = data.char0;
          SALInstance.short0 = data.short0;
          SALInstance.int0 = data.int0;
          SALInstance.long0 = data.long0;
          SALInstance.longLong0 = data.longLong0;
          SALInstance.octet0 = data.octet0;
          SALInstance.unsignedShort0 = data.unsignedShort0;
          SALInstance.unsignedInt0 = data.unsignedInt0;
          SALInstance.unsignedLong0 = data.unsignedLong0;
          SALInstance.float0 = data.float0;
          SALInstance.double0 = data.double0;
          SALInstance.string0 = data.string0;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_setScalars_0fcc702bDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setscalars>Test_command_setScalars</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_setScalars data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_setScalars_0fcc702bSeqHolder SALInstance = new command_setScalars_0fcc702bSeqHolder();
    int actorIdx = SAL__Test_command_setScalars_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_setScalars_0fcc702bDataReader SALReader = command_setScalars_0fcc702bDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_setScalars ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.boolean0 = SALInstance.value[j].boolean0;
           data.byte0 = SALInstance.value[j].byte0;
           data.char0 = SALInstance.value[j].char0;
           data.short0 = SALInstance.value[j].short0;
           data.int0 = SALInstance.value[j].int0;
           data.long0 = SALInstance.value[j].long0;
           data.longLong0 = SALInstance.value[j].longLong0;
           data.octet0 = SALInstance.value[j].octet0;
           data.unsignedShort0 = SALInstance.value[j].unsignedShort0;
           data.unsignedInt0 = SALInstance.value[j].unsignedInt0;
           data.unsignedLong0 = SALInstance.value[j].unsignedLong0;
           data.float0 = SALInstance.value[j].float0;
           data.double0 = SALInstance.value[j].double0;
           data.string0 = SALInstance.value[j].string0;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setscalars>Test_command_setScalars</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_setScalars
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_setScalars data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_setScalars_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_setScalars or getNextSample_command_setScalars
  */
  public int flushSamples(Test.command_setScalars data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_setScalars_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setvalue>Test_command_setValue</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_setValue data)
  {
          int status = SAL__OK;
          command_setValue_caab4364 SALInstance = new command_setValue_caab4364();
    int actorIdx = SAL__Test_command_setValue_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_setValue_caab4364DataWriter SALWriter = command_setValue_caab4364DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "caab4364";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_setValue] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
          SALInstance.parametersAndValues = data.parametersAndValues;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_setValue_caab4364DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setvalue>Test_command_setValue</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_setValue data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_setValue_caab4364SeqHolder SALInstance = new command_setValue_caab4364SeqHolder();
    int actorIdx = SAL__Test_command_setValue_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_setValue_caab4364DataReader SALReader = command_setValue_caab4364DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_setValue ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.parametersAndValues = SALInstance.value[j].parametersAndValues;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setvalue>Test_command_setValue</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_setValue
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_setValue data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_setValue_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_setValue or getNextSample_command_setValue
  */
  public int flushSamples(Test.command_setValue data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_setValue_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#standby>Test_command_standby</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_standby data)
  {
          int status = SAL__OK;
          command_standby_7054c900 SALInstance = new command_standby_7054c900();
    int actorIdx = SAL__Test_command_standby_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_standby_7054c900DataWriter SALWriter = command_standby_7054c900DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "7054c900";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_standby] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_standby_7054c900DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#standby>Test_command_standby</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_standby data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_standby_7054c900SeqHolder SALInstance = new command_standby_7054c900SeqHolder();
    int actorIdx = SAL__Test_command_standby_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_standby_7054c900DataReader SALReader = command_standby_7054c900DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_standby ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#standby>Test_command_standby</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_standby
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_standby data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_standby_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_standby or getNextSample_command_standby
  */
  public int flushSamples(Test.command_standby data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_standby_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#start>Test_command_start</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_start data)
  {
          int status = SAL__OK;
          command_start_74351d61 SALInstance = new command_start_74351d61();
    int actorIdx = SAL__Test_command_start_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_start_74351d61DataWriter SALWriter = command_start_74351d61DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "74351d61";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_start] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
          SALInstance.settingsToApply = data.settingsToApply;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_start_74351d61DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#start>Test_command_start</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_start data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_start_74351d61SeqHolder SALInstance = new command_start_74351d61SeqHolder();
    int actorIdx = SAL__Test_command_start_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_start_74351d61DataReader SALReader = command_start_74351d61DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_start ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.settingsToApply = SALInstance.value[j].settingsToApply;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#start>Test_command_start</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_start
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_start data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_start_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_start or getNextSample_command_start
  */
  public int flushSamples(Test.command_start data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_start_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#wait>Test_command_wait</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.command_wait data)
  {
          int status = SAL__OK;
          command_wait_1b2e8a6f SALInstance = new command_wait_1b2e8a6f();
    int actorIdx = SAL__Test_command_wait_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    command_wait_1b2e8a6fDataWriter SALWriter = command_wait_1b2e8a6fDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "1b2e8a6f";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample command_wait] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
          SALInstance.ack = data.ack;
          SALInstance.duration = data.duration;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "command_wait_1b2e8a6fDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#wait>Test_command_wait</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.command_wait data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          command_wait_1b2e8a6fSeqHolder SALInstance = new command_wait_1b2e8a6fSeqHolder();
    int actorIdx = SAL__Test_command_wait_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  command_wait_1b2e8a6fDataReader SALReader = command_wait_1b2e8a6fDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample command_wait ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.ack = SALInstance.value[j].ack;
           data.duration = SALInstance.value[j].duration;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#wait>Test_command_wait</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_command_wait
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.command_wait data)
  {
    int status = -1;
    int actorIdx = SAL__Test_command_wait_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_command_wait or getNextSample_command_wait
  */
  public int flushSamples(Test.command_wait data)
  {
          int status = -1;
    int actorIdx = SAL__Test_command_wait_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#appliedsettingsmatchstart>Test_logevent_appliedSettingsMatchStart</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_appliedSettingsMatchStart data)
  {
          int status = SAL__OK;
          logevent_appliedSettingsMatchStart_77f17e02 SALInstance = new logevent_appliedSettingsMatchStart_77f17e02();
    int actorIdx = SAL__Test_logevent_appliedSettingsMatchStart_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_appliedSettingsMatchStart_77f17e02DataWriter SALWriter = logevent_appliedSettingsMatchStart_77f17e02DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "77f17e02";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_appliedSettingsMatchStart] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.appliedSettingsMatchStartIsTrue = data.appliedSettingsMatchStartIsTrue;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_appliedSettingsMatchStart_77f17e02DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#appliedsettingsmatchstart>Test_logevent_appliedSettingsMatchStart</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_appliedSettingsMatchStart data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_appliedSettingsMatchStart_77f17e02SeqHolder SALInstance = new logevent_appliedSettingsMatchStart_77f17e02SeqHolder();
    int actorIdx = SAL__Test_logevent_appliedSettingsMatchStart_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_appliedSettingsMatchStart_77f17e02DataReader SALReader = logevent_appliedSettingsMatchStart_77f17e02DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_appliedSettingsMatchStart ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.appliedSettingsMatchStartIsTrue = SALInstance.value[j].appliedSettingsMatchStartIsTrue;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#appliedsettingsmatchstart>Test_logevent_appliedSettingsMatchStart</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_appliedSettingsMatchStart
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_appliedSettingsMatchStart data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_appliedSettingsMatchStart_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_appliedSettingsMatchStart or getNextSample_logevent_appliedSettingsMatchStart
  */
  public int flushSamples(Test.logevent_appliedSettingsMatchStart data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_appliedSettingsMatchStart_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_logevent_arrays</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_arrays data)
  {
          int status = SAL__OK;
          logevent_arrays_5720d768 SALInstance = new logevent_arrays_5720d768();
    int actorIdx = SAL__Test_logevent_arrays_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_arrays_5720d768DataWriter SALWriter = logevent_arrays_5720d768DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "5720d768";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_arrays] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           System.arraycopy(data.boolean0,0,SALInstance.boolean0,0,5);
           System.arraycopy(data.byte0,0,SALInstance.byte0,0,5);
           System.arraycopy(data.short0,0,SALInstance.short0,0,5);
           System.arraycopy(data.int0,0,SALInstance.int0,0,5);
           System.arraycopy(data.long0,0,SALInstance.long0,0,5);
           System.arraycopy(data.longLong0,0,SALInstance.longLong0,0,5);
           System.arraycopy(data.octet0,0,SALInstance.octet0,0,5);
           System.arraycopy(data.unsignedShort0,0,SALInstance.unsignedShort0,0,5);
           System.arraycopy(data.unsignedInt0,0,SALInstance.unsignedInt0,0,5);
           System.arraycopy(data.unsignedLong0,0,SALInstance.unsignedLong0,0,5);
           System.arraycopy(data.float0,0,SALInstance.float0,0,5);
           System.arraycopy(data.double0,0,SALInstance.double0,0,5);
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_arrays_5720d768DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_logevent_arrays</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_arrays data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_arrays_5720d768SeqHolder SALInstance = new logevent_arrays_5720d768SeqHolder();
    int actorIdx = SAL__Test_logevent_arrays_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_arrays_5720d768DataReader SALReader = logevent_arrays_5720d768DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_arrays ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           System.arraycopy(SALInstance.value[j].boolean0,0,data.boolean0,0,5);
           System.arraycopy(SALInstance.value[j].byte0,0,data.byte0,0,5);
           System.arraycopy(SALInstance.value[j].short0,0,data.short0,0,5);
           System.arraycopy(SALInstance.value[j].int0,0,data.int0,0,5);
           System.arraycopy(SALInstance.value[j].long0,0,data.long0,0,5);
           System.arraycopy(SALInstance.value[j].longLong0,0,data.longLong0,0,5);
           System.arraycopy(SALInstance.value[j].octet0,0,data.octet0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedShort0,0,data.unsignedShort0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedInt0,0,data.unsignedInt0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedLong0,0,data.unsignedLong0,0,5);
           System.arraycopy(SALInstance.value[j].float0,0,data.float0,0,5);
           System.arraycopy(SALInstance.value[j].double0,0,data.double0,0,5);
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_logevent_arrays</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_arrays
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_arrays data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_arrays_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_arrays or getNextSample_logevent_arrays
  */
  public int flushSamples(Test.logevent_arrays data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_arrays_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#authlist>Test_logevent_authList</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_authList data)
  {
          int status = SAL__OK;
          logevent_authList_3447a91e SALInstance = new logevent_authList_3447a91e();
    int actorIdx = SAL__Test_logevent_authList_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_authList_3447a91eDataWriter SALWriter = logevent_authList_3447a91eDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "3447a91e";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_authList] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.authorizedUsers = data.authorizedUsers;
           SALInstance.nonAuthorizedCSCs = data.nonAuthorizedCSCs;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_authList_3447a91eDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#authlist>Test_logevent_authList</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_authList data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_authList_3447a91eSeqHolder SALInstance = new logevent_authList_3447a91eSeqHolder();
    int actorIdx = SAL__Test_logevent_authList_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_authList_3447a91eDataReader SALReader = logevent_authList_3447a91eDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_authList ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.authorizedUsers = SALInstance.value[j].authorizedUsers;
           data.nonAuthorizedCSCs = SALInstance.value[j].nonAuthorizedCSCs;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#authlist>Test_logevent_authList</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_authList
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_authList data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_authList_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_authList or getNextSample_logevent_authList
  */
  public int flushSamples(Test.logevent_authList data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_authList_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#errorcode>Test_logevent_errorCode</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_errorCode data)
  {
          int status = SAL__OK;
          logevent_errorCode_12906fff SALInstance = new logevent_errorCode_12906fff();
    int actorIdx = SAL__Test_logevent_errorCode_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_errorCode_12906fffDataWriter SALWriter = logevent_errorCode_12906fffDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "12906fff";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_errorCode] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.errorCode = data.errorCode;
           SALInstance.errorReport = data.errorReport;
           SALInstance.traceback = data.traceback;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_errorCode_12906fffDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#errorcode>Test_logevent_errorCode</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_errorCode data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_errorCode_12906fffSeqHolder SALInstance = new logevent_errorCode_12906fffSeqHolder();
    int actorIdx = SAL__Test_logevent_errorCode_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_errorCode_12906fffDataReader SALReader = logevent_errorCode_12906fffDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_errorCode ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.errorCode = SALInstance.value[j].errorCode;
           data.errorReport = SALInstance.value[j].errorReport;
           data.traceback = SALInstance.value[j].traceback;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#errorcode>Test_logevent_errorCode</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_errorCode
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_errorCode data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_errorCode_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_errorCode or getNextSample_logevent_errorCode
  */
  public int flushSamples(Test.logevent_errorCode data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_errorCode_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#heartbeat>Test_logevent_heartbeat</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_heartbeat data)
  {
          int status = SAL__OK;
          logevent_heartbeat_57130bcb SALInstance = new logevent_heartbeat_57130bcb();
    int actorIdx = SAL__Test_logevent_heartbeat_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_heartbeat_57130bcbDataWriter SALWriter = logevent_heartbeat_57130bcbDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "57130bcb";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_heartbeat] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.heartbeat = data.heartbeat;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_heartbeat_57130bcbDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#heartbeat>Test_logevent_heartbeat</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_heartbeat data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_heartbeat_57130bcbSeqHolder SALInstance = new logevent_heartbeat_57130bcbSeqHolder();
    int actorIdx = SAL__Test_logevent_heartbeat_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_heartbeat_57130bcbDataReader SALReader = logevent_heartbeat_57130bcbDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_heartbeat ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.heartbeat = SALInstance.value[j].heartbeat;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#heartbeat>Test_logevent_heartbeat</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_heartbeat
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_heartbeat data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_heartbeat_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_heartbeat or getNextSample_logevent_heartbeat
  */
  public int flushSamples(Test.logevent_heartbeat data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_heartbeat_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#loglevel>Test_logevent_logLevel</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_logLevel data)
  {
          int status = SAL__OK;
          logevent_logLevel_7aeafb06 SALInstance = new logevent_logLevel_7aeafb06();
    int actorIdx = SAL__Test_logevent_logLevel_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_logLevel_7aeafb06DataWriter SALWriter = logevent_logLevel_7aeafb06DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "7aeafb06";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_logLevel] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.level = data.level;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_logLevel_7aeafb06DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#loglevel>Test_logevent_logLevel</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_logLevel data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_logLevel_7aeafb06SeqHolder SALInstance = new logevent_logLevel_7aeafb06SeqHolder();
    int actorIdx = SAL__Test_logevent_logLevel_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_logLevel_7aeafb06DataReader SALReader = logevent_logLevel_7aeafb06DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_logLevel ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.level = SALInstance.value[j].level;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#loglevel>Test_logevent_logLevel</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_logLevel
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_logLevel data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_logLevel_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_logLevel or getNextSample_logevent_logLevel
  */
  public int flushSamples(Test.logevent_logLevel data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_logLevel_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#logmessage>Test_logevent_logMessage</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_logMessage data)
  {
          int status = SAL__OK;
          logevent_logMessage_f4568bfc SALInstance = new logevent_logMessage_f4568bfc();
    int actorIdx = SAL__Test_logevent_logMessage_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_logMessage_f4568bfcDataWriter SALWriter = logevent_logMessage_f4568bfcDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "f4568bfc";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_logMessage] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.name = data.name;
           SALInstance.level = data.level;
           SALInstance.message = data.message;
           SALInstance.traceback = data.traceback;
           SALInstance.filePath = data.filePath;
           SALInstance.functionName = data.functionName;
           SALInstance.lineNumber = data.lineNumber;
           SALInstance.process = data.process;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_logMessage_f4568bfcDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#logmessage>Test_logevent_logMessage</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_logMessage data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_logMessage_f4568bfcSeqHolder SALInstance = new logevent_logMessage_f4568bfcSeqHolder();
    int actorIdx = SAL__Test_logevent_logMessage_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_logMessage_f4568bfcDataReader SALReader = logevent_logMessage_f4568bfcDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_logMessage ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.name = SALInstance.value[j].name;
           data.level = SALInstance.value[j].level;
           data.message = SALInstance.value[j].message;
           data.traceback = SALInstance.value[j].traceback;
           data.filePath = SALInstance.value[j].filePath;
           data.functionName = SALInstance.value[j].functionName;
           data.lineNumber = SALInstance.value[j].lineNumber;
           data.process = SALInstance.value[j].process;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#logmessage>Test_logevent_logMessage</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_logMessage
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_logMessage data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_logMessage_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_logMessage or getNextSample_logevent_logMessage
  */
  public int flushSamples(Test.logevent_logMessage data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_logMessage_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_logevent_scalars</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_scalars data)
  {
          int status = SAL__OK;
          logevent_scalars_1d41189e SALInstance = new logevent_scalars_1d41189e();
    int actorIdx = SAL__Test_logevent_scalars_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_scalars_1d41189eDataWriter SALWriter = logevent_scalars_1d41189eDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "1d41189e";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_scalars] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.boolean0 = data.boolean0;
           SALInstance.byte0 = data.byte0;
           SALInstance.char0 = data.char0;
           SALInstance.short0 = data.short0;
           SALInstance.int0 = data.int0;
           SALInstance.long0 = data.long0;
           SALInstance.longLong0 = data.longLong0;
           SALInstance.octet0 = data.octet0;
           SALInstance.unsignedShort0 = data.unsignedShort0;
           SALInstance.unsignedInt0 = data.unsignedInt0;
           SALInstance.unsignedLong0 = data.unsignedLong0;
           SALInstance.float0 = data.float0;
           SALInstance.double0 = data.double0;
           SALInstance.string0 = data.string0;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_scalars_1d41189eDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_logevent_scalars</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_scalars data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_scalars_1d41189eSeqHolder SALInstance = new logevent_scalars_1d41189eSeqHolder();
    int actorIdx = SAL__Test_logevent_scalars_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_scalars_1d41189eDataReader SALReader = logevent_scalars_1d41189eDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_scalars ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.boolean0 = SALInstance.value[j].boolean0;
           data.byte0 = SALInstance.value[j].byte0;
           data.char0 = SALInstance.value[j].char0;
           data.short0 = SALInstance.value[j].short0;
           data.int0 = SALInstance.value[j].int0;
           data.long0 = SALInstance.value[j].long0;
           data.longLong0 = SALInstance.value[j].longLong0;
           data.octet0 = SALInstance.value[j].octet0;
           data.unsignedShort0 = SALInstance.value[j].unsignedShort0;
           data.unsignedInt0 = SALInstance.value[j].unsignedInt0;
           data.unsignedLong0 = SALInstance.value[j].unsignedLong0;
           data.float0 = SALInstance.value[j].float0;
           data.double0 = SALInstance.value[j].double0;
           data.string0 = SALInstance.value[j].string0;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_logevent_scalars</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_scalars
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_scalars data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_scalars_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_scalars or getNextSample_logevent_scalars
  */
  public int flushSamples(Test.logevent_scalars data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_scalars_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingversions>Test_logevent_settingVersions</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_settingVersions data)
  {
          int status = SAL__OK;
          logevent_settingVersions_11e4d332 SALInstance = new logevent_settingVersions_11e4d332();
    int actorIdx = SAL__Test_logevent_settingVersions_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_settingVersions_11e4d332DataWriter SALWriter = logevent_settingVersions_11e4d332DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "11e4d332";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_settingVersions] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.recommendedSettingsVersion = data.recommendedSettingsVersion;
           SALInstance.recommendedSettingsLabels = data.recommendedSettingsLabels;
           SALInstance.settingsUrl = data.settingsUrl;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_settingVersions_11e4d332DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingversions>Test_logevent_settingVersions</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_settingVersions data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_settingVersions_11e4d332SeqHolder SALInstance = new logevent_settingVersions_11e4d332SeqHolder();
    int actorIdx = SAL__Test_logevent_settingVersions_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_settingVersions_11e4d332DataReader SALReader = logevent_settingVersions_11e4d332DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_settingVersions ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.recommendedSettingsVersion = SALInstance.value[j].recommendedSettingsVersion;
           data.recommendedSettingsLabels = SALInstance.value[j].recommendedSettingsLabels;
           data.settingsUrl = SALInstance.value[j].settingsUrl;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingversions>Test_logevent_settingVersions</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_settingVersions
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_settingVersions data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_settingVersions_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_settingVersions or getNextSample_logevent_settingVersions
  */
  public int flushSamples(Test.logevent_settingVersions data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_settingVersions_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingsapplied>Test_logevent_settingsApplied</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_settingsApplied data)
  {
          int status = SAL__OK;
          logevent_settingsApplied_02b3cc9e SALInstance = new logevent_settingsApplied_02b3cc9e();
    int actorIdx = SAL__Test_logevent_settingsApplied_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_settingsApplied_02b3cc9eDataWriter SALWriter = logevent_settingsApplied_02b3cc9eDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "02b3cc9e";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_settingsApplied] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.settingsVersion = data.settingsVersion;
           SALInstance.otherSettingsEvents = data.otherSettingsEvents;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_settingsApplied_02b3cc9eDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingsapplied>Test_logevent_settingsApplied</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_settingsApplied data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_settingsApplied_02b3cc9eSeqHolder SALInstance = new logevent_settingsApplied_02b3cc9eSeqHolder();
    int actorIdx = SAL__Test_logevent_settingsApplied_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_settingsApplied_02b3cc9eDataReader SALReader = logevent_settingsApplied_02b3cc9eDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_settingsApplied ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.settingsVersion = SALInstance.value[j].settingsVersion;
           data.otherSettingsEvents = SALInstance.value[j].otherSettingsEvents;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingsapplied>Test_logevent_settingsApplied</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_settingsApplied
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_settingsApplied data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_settingsApplied_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_settingsApplied or getNextSample_logevent_settingsApplied
  */
  public int flushSamples(Test.logevent_settingsApplied data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_settingsApplied_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#simulationmode>Test_logevent_simulationMode</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_simulationMode data)
  {
          int status = SAL__OK;
          logevent_simulationMode_fcccc7ae SALInstance = new logevent_simulationMode_fcccc7ae();
    int actorIdx = SAL__Test_logevent_simulationMode_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_simulationMode_fcccc7aeDataWriter SALWriter = logevent_simulationMode_fcccc7aeDataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "fcccc7ae";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_simulationMode] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.mode = data.mode;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_simulationMode_fcccc7aeDataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#simulationmode>Test_logevent_simulationMode</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_simulationMode data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_simulationMode_fcccc7aeSeqHolder SALInstance = new logevent_simulationMode_fcccc7aeSeqHolder();
    int actorIdx = SAL__Test_logevent_simulationMode_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_simulationMode_fcccc7aeDataReader SALReader = logevent_simulationMode_fcccc7aeDataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_simulationMode ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.mode = SALInstance.value[j].mode;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#simulationmode>Test_logevent_simulationMode</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_simulationMode
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_simulationMode data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_simulationMode_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_simulationMode or getNextSample_logevent_simulationMode
  */
  public int flushSamples(Test.logevent_simulationMode data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_simulationMode_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#softwareversions>Test_logevent_softwareVersions</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_softwareVersions data)
  {
          int status = SAL__OK;
          logevent_softwareVersions_1f4be1b2 SALInstance = new logevent_softwareVersions_1f4be1b2();
    int actorIdx = SAL__Test_logevent_softwareVersions_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_softwareVersions_1f4be1b2DataWriter SALWriter = logevent_softwareVersions_1f4be1b2DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "1f4be1b2";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_softwareVersions] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.salVersion = data.salVersion;
           SALInstance.xmlVersion = data.xmlVersion;
           SALInstance.openSpliceVersion = data.openSpliceVersion;
           SALInstance.cscVersion = data.cscVersion;
           SALInstance.subsystemVersions = data.subsystemVersions;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_softwareVersions_1f4be1b2DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#softwareversions>Test_logevent_softwareVersions</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_softwareVersions data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_softwareVersions_1f4be1b2SeqHolder SALInstance = new logevent_softwareVersions_1f4be1b2SeqHolder();
    int actorIdx = SAL__Test_logevent_softwareVersions_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_softwareVersions_1f4be1b2DataReader SALReader = logevent_softwareVersions_1f4be1b2DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_softwareVersions ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.salVersion = SALInstance.value[j].salVersion;
           data.xmlVersion = SALInstance.value[j].xmlVersion;
           data.openSpliceVersion = SALInstance.value[j].openSpliceVersion;
           data.cscVersion = SALInstance.value[j].cscVersion;
           data.subsystemVersions = SALInstance.value[j].subsystemVersions;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#softwareversions>Test_logevent_softwareVersions</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_softwareVersions
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_softwareVersions data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_softwareVersions_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_softwareVersions or getNextSample_logevent_softwareVersions
  */
  public int flushSamples(Test.logevent_softwareVersions data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_softwareVersions_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#summarystate>Test_logevent_summaryState</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.logevent_summaryState data)
  {
          int status = SAL__OK;
          logevent_summaryState_7351a054 SALInstance = new logevent_summaryState_7351a054();
    int actorIdx = SAL__Test_logevent_summaryState_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    logevent_summaryState_7351a054DataWriter SALWriter = logevent_summaryState_7351a054DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "7351a054";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample logevent_summaryState] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.summaryState = data.summaryState;
           SALInstance.priority = data.priority;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "logevent_summaryState_7351a054DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#summarystate>Test_logevent_summaryState</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.logevent_summaryState data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          logevent_summaryState_7351a054SeqHolder SALInstance = new logevent_summaryState_7351a054SeqHolder();
    int actorIdx = SAL__Test_logevent_summaryState_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  logevent_summaryState_7351a054DataReader SALReader = logevent_summaryState_7351a054DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample logevent_summaryState ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.summaryState = SALInstance.value[j].summaryState;
           data.priority = SALInstance.value[j].priority;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#summarystate>Test_logevent_summaryState</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_logevent_summaryState
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.logevent_summaryState data)
  {
    int status = -1;
    int actorIdx = SAL__Test_logevent_summaryState_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_logevent_summaryState or getNextSample_logevent_summaryState
  */
  public int flushSamples(Test.logevent_summaryState data)
  {
          int status = -1;
    int actorIdx = SAL__Test_logevent_summaryState_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_scalars</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.scalars data)
  {
          int status = SAL__OK;
          scalars_e4001923 SALInstance = new scalars_e4001923();
    int actorIdx = SAL__Test_scalars_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    scalars_e4001923DataWriter SALWriter = scalars_e4001923DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "e4001923";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample scalars] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }
           SALInstance.boolean0 = data.boolean0;
           SALInstance.byte0 = data.byte0;
           SALInstance.char0 = data.char0;
           SALInstance.short0 = data.short0;
           SALInstance.int0 = data.int0;
           SALInstance.long0 = data.long0;
           SALInstance.longLong0 = data.longLong0;
           SALInstance.octet0 = data.octet0;
           SALInstance.unsignedShort0 = data.unsignedShort0;
           SALInstance.unsignedInt0 = data.unsignedInt0;
           SALInstance.unsignedLong0 = data.unsignedLong0;
           SALInstance.float0 = data.float0;
           SALInstance.double0 = data.double0;
           SALInstance.string0 = data.string0;

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "scalars_e4001923DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_scalars</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.scalars data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          scalars_e4001923SeqHolder SALInstance = new scalars_e4001923SeqHolder();
    int actorIdx = SAL__Test_scalars_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  scalars_e4001923DataReader SALReader = scalars_e4001923DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample scalars ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
           data.boolean0 = SALInstance.value[j].boolean0;
           data.byte0 = SALInstance.value[j].byte0;
           data.char0 = SALInstance.value[j].char0;
           data.short0 = SALInstance.value[j].short0;
           data.int0 = SALInstance.value[j].int0;
           data.long0 = SALInstance.value[j].long0;
           data.longLong0 = SALInstance.value[j].longLong0;
           data.octet0 = SALInstance.value[j].octet0;
           data.unsignedShort0 = SALInstance.value[j].unsignedShort0;
           data.unsignedInt0 = SALInstance.value[j].unsignedInt0;
           data.unsignedLong0 = SALInstance.value[j].unsignedLong0;
           data.float0 = SALInstance.value[j].float0;
           data.double0 = SALInstance.value[j].double0;
           data.string0 = SALInstance.value[j].string0;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_scalars</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_scalars
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.scalars data)
  {
    int status = -1;
    int actorIdx = SAL__Test_scalars_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_scalars or getNextSample_scalars
  */
  public int flushSamples(Test.scalars data)
  {
          int status = -1;
    int actorIdx = SAL__Test_scalars_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


/** Publish a sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#ackcmd>Test_ackcmd</A> DDS topic. A publisher must already have been set up
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int putSample(Test.ackcmd data)
  {
          int status = SAL__OK;
          ackcmd_902e1de4 SALInstance = new ackcmd_902e1de4();
    int actorIdx = SAL__Test_ackcmd_ACTOR;
    if ( sal[actorIdx].isWriter == false ) {
      createWriter(actorIdx,false);
      sal[actorIdx].isWriter = true;
    }
    DataWriter dwriter = getWriter(actorIdx);
    ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
    SALInstance.private_revCode = "902e1de4";
    SALInstance.private_sndStamp = getCurrentTime();
    SALInstance.private_identity = CSC_identity;
    SALInstance.private_origin = origin;
    SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
    sal[actorIdx].sndSeqNum++;
    if (debugLevel > 0) {
      System.out.println("=== [putSample ackcmd] writing a message containing :");
      System.out.println("    revCode  : " + SALInstance.private_revCode);
      System.out.println("    sndStamp  : " + SALInstance.private_sndStamp);
    }

           SALInstance.TestID = subsystemID;
           long dataHandle = SALWriter.register_instance(SALInstance);
     status = SALWriter.write(SALInstance, dataHandle);
     checkStatus(status, "ackcmd_902e1de4DataWriter.write");
           SALWriter.dispose(SALInstance, dataHandle);

    return status;
  }


/** Receive the latest sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#ackcmd>Test_ackcmd</A> DDS topic. A subscriber must already have been set up.
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are skipped over and only the most recent is supplied.
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getSample(Test.ackcmd data)
  {
    int status =  -1;
          int last = SAL__NO_UPDATES;
          int numsamp;
          ackcmd_902e1de4SeqHolder SALInstance = new ackcmd_902e1de4SeqHolder();
    int actorIdx = SAL__Test_ackcmd_ACTOR;
    if ( sal[actorIdx].isReader == false ) {
      // Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
        createContentFilteredTopic(actorIdx,"filteredtopic", sFilter, expr);
    // create DataReader
    createReader(actorIdx,true);

	    sal[actorIdx].isReader = true;
	  }
	  DataReader dreader = getReader(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(SALInstance, infoSeq, sal[actorIdx].maxSamples,
					NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value,
					ANY_INSTANCE_STATE.value);
          numsamp = SALInstance.value.length;
          if (numsamp > 0) {
      if (debugLevel > 0) {
    for (int i = 0; i < numsamp; i++) {
        System.out.println("=== [getSample ackcmd ] message received :" + i);
        System.out.println("    revCode  : "
            + SALInstance.value[i].private_revCode);
              System.out.println("     sndStamp  : " + SALInstance.value[i].private_sndStamp);
        System.out.println("  sample_state : " + infoSeq.value[i].sample_state);
        System.out.println("    view_state : " + infoSeq.value[i].view_state);
        System.out.println("instance_state : " + infoSeq.value[i].instance_state);
    }
      }
            int j=numsamp-1;
            if (infoSeq.value[j].valid_data) {
        double rcvdTime = getCurrentTime();
        double dTime = rcvdTime - SALInstance.value[j].private_sndStamp;
        if ( dTime < sal[actorIdx].sampleAge ) {
                   data.private_sndStamp = SALInstance.value[j].private_sndStamp;
                   last = SAL__OK;
                } else {
                   System.out.println("dropped sample : " + rcvdTime + " " + SALInstance.value[j].private_sndStamp);
                   last = SAL__NO_UPDATES;
                }
            }
          } else {
              last = SAL__NO_UPDATES;
          }
          status = SALReader.return_loan (SALInstance, infoSeq);
    return last;
  }

/** Receive the next sample of the <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#ackcmd>Test_ackcmd</A> DDS topic from the history cache. 
  * A subscriber must already have been set up
  * If there are no samples available then SAL__NO_UPDATES is returned, otherwise SAL__OK is returned.
  * If there are multiple samples in the history cache, they are iterated over by consecutive 
  * calls to getNextSample_ackcmd
  * @param data The payload of the sample as defined in the XML for Test
  */
  public int getNextSample(Test.ackcmd data)
  {
    int status = -1;
    int actorIdx = SAL__Test_ackcmd_ACTOR;
          int saveMax = sal[actorIdx].maxSamples; 
          sal[actorIdx].maxSamples = 1;
          status = getSample(data);
          sal[actorIdx].maxSamples = saveMax;
          return status;
  }

/** Empty the history cache of samples. After this only newly published samples
  * will be available to getSample_ackcmd or getNextSample_ackcmd
  */
  public int flushSamples(Test.ackcmd data)
  {
          int status = -1;
    int actorIdx = SAL__Test_ackcmd_ACTOR;
          sal[actorIdx].maxSamples = DDS.LENGTH_UNLIMITED.value;
          sal[actorIdx].sampleAge = -1.0;
          status = getSample(data);
          sal[actorIdx].sampleAge = 1.0e20;
          return SAL__OK;
  }


	public void salCommand(String cmdAlias)
	{
          int actorIdx = getActorIndex(cmdAlias);
	  String stopic1="keyedCommand";
	  String stopic2="keyedResponse";
	  String sresponse="Test_ackcmd";

	  // create domain participant
	  createParticipant(domainName);

	  //create Publisher
	  createPublisher(actorIdx);

	  //create types
	  salTypeSupport(actorIdx);

	  //create Topics
	  createTopic(actorIdx,cmdAlias);
	  boolean autodispose_unregistered_instances = true;
	  createWriter(actorIdx,autodispose_unregistered_instances);
	  sal[actorIdx].isWriter = true;
	  sal[actorIdx].isCommand = true;
          sal[SAL__Test_ackcmd_ACTOR].sampleAge = 1.0;
          sal[actorIdx].sndSeqNum = (int)getCurrentTime() + 32768*actorIdx;
	
          if ( sal[SAL__Test_ackcmd_ACTOR].isReader == false ) {
	    createSubscriber(SAL__Test_ackcmd_ACTOR);
	    ackcmd_902e1de4TypeSupport mtr = new ackcmd_902e1de4TypeSupport();
	    registerType2(SAL__Test_ackcmd_ACTOR,mtr);
	    createTopic2(SAL__Test_ackcmd_ACTOR,sresponse);
	    //create a reader for responses


  	    // Filter expr
            String expr[] = new String[0];
            String sFilter = "TestID = " + subsystemID;
    	    createContentFilteredTopic2(SAL__Test_ackcmd_ACTOR,"filteredResponse", sFilter, expr);

	    // create DataReader
 	    createReader2(SAL__Test_ackcmd_ACTOR,false);


 	    sal[SAL__Test_ackcmd_ACTOR].isReader = true;
          }

	}

	public void salProcessor(String cmdAlias)
	{
          int actorIdx = getActorIndex(cmdAlias);
	  String stopic1="keyedCommand";
	  String stopic2="keyedResponse";
	  String sresponse="Test_ackcmd";

	  // create domain participant
	  createParticipant(domainName);

	  createSubscriber(actorIdx);

	  //create types
	  salTypeSupport(actorIdx);

	  //create Topics
	  createTopic(actorIdx,cmdAlias);

	  //create a reader for commands


  	  // Filter expr
          String expr[] = new String[0];
          String sFilter = "TestID = " + subsystemID;
          String fCmd = "filteredCmd" + sal[actorIdx].topicHandle;
    	  createContentFilteredTopic(actorIdx,fCmd, sFilter, expr);
 	  createReader(actorIdx,false);


          if (sal[actorIdx].isProcessor == false) {
  	    //create Publisher
	    createPublisher(SAL__Test_ackcmd_ACTOR);
	    ackcmd_902e1de4TypeSupport mtr = new ackcmd_902e1de4TypeSupport();
	    registerType2(SAL__Test_ackcmd_ACTOR,mtr);
	    createTopic2(SAL__Test_ackcmd_ACTOR,sresponse);
   	    boolean autodispose_unregistered_instances = true;
	    createWriter2(SAL__Test_ackcmd_ACTOR,autodispose_unregistered_instances);
	    sal[SAL__Test_ackcmd_ACTOR].isWriter = true;
          }
	  sal[actorIdx].isProcessor = true;
          sal[actorIdx].sampleAge = 1.0;
	}


/** Issue the setScalars command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setscalars>Test_setScalars</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_setScalars( command_setScalars data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_setScalars_0fcc702b SALInstance = new command_setScalars_0fcc702b();
          int status;
          int actorIdx = SAL__Test_command_setScalars_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_setScalars_0fcc702bDataWriter SALWriter = command_setScalars_0fcc702bDataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "0fcc702b";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);
          SALInstance.boolean0 = data.boolean0;
          SALInstance.byte0 = data.byte0;
          SALInstance.char0 = data.char0;
          SALInstance.short0 = data.short0;
          SALInstance.int0 = data.int0;
          SALInstance.long0 = data.long0;
          SALInstance.longLong0 = data.longLong0;
          SALInstance.octet0 = data.octet0;
          SALInstance.unsignedShort0 = data.unsignedShort0;
          SALInstance.unsignedInt0 = data.unsignedInt0;
          SALInstance.unsignedLong0 = data.unsignedLong0;
          SALInstance.float0 = data.float0;
          SALInstance.double0 = data.double0;
          SALInstance.string0 = data.string0;

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] setScalars writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the setScalars command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setscalars>Test_setScalars</A>
  */
	public int acceptCommand_setScalars( Test.command_setScalars data )
	{
                command_setScalars_0fcc702bSeqHolder SALInstance = new command_setScalars_0fcc702bSeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setScalars_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_setScalars_0fcc702bDataReader SALReader = command_setScalars_0fcc702bDataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] setScalars reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";
           data.boolean0 = SALInstance.value[j].boolean0;
           data.byte0 = SALInstance.value[j].byte0;
           data.char0 = SALInstance.value[j].char0;
           data.short0 = SALInstance.value[j].short0;
           data.int0 = SALInstance.value[j].int0;
           data.long0 = SALInstance.value[j].long0;
           data.longLong0 = SALInstance.value[j].longLong0;
           data.octet0 = SALInstance.value[j].octet0;
           data.unsignedShort0 = SALInstance.value[j].unsignedShort0;
           data.unsignedInt0 = SALInstance.value[j].unsignedInt0;
           data.unsignedLong0 = SALInstance.value[j].unsignedLong0;
           data.float0 = SALInstance.value[j].float0;
           data.double0 = SALInstance.value[j].double0;
           data.string0 = SALInstance.value[j].string0;

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_setScalars( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_setScalars_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setScalars(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setScalars] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setScalars] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_setScalars( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setScalars(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_setScalars] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_setScalars(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_setScalars_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_setScalars] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_setScalars] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_setScalars( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setScalars_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_setScalars] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the setArrays command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setarrays>Test_setArrays</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_setArrays( command_setArrays data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_setArrays_e11a567a SALInstance = new command_setArrays_e11a567a();
          int status;
          int actorIdx = SAL__Test_command_setArrays_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_setArrays_e11a567aDataWriter SALWriter = command_setArrays_e11a567aDataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "e11a567a";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);
          System.arraycopy(data.boolean0,0,SALInstance.boolean0,0,5);
          System.arraycopy(data.byte0,0,SALInstance.byte0,0,5);
          System.arraycopy(data.short0,0,SALInstance.short0,0,5);
          System.arraycopy(data.int0,0,SALInstance.int0,0,5);
          System.arraycopy(data.long0,0,SALInstance.long0,0,5);
          System.arraycopy(data.longLong0,0,SALInstance.longLong0,0,5);
          System.arraycopy(data.octet0,0,SALInstance.octet0,0,5);
          System.arraycopy(data.unsignedShort0,0,SALInstance.unsignedShort0,0,5);
          System.arraycopy(data.unsignedInt0,0,SALInstance.unsignedInt0,0,5);
          System.arraycopy(data.unsignedLong0,0,SALInstance.unsignedLong0,0,5);
          System.arraycopy(data.float0,0,SALInstance.float0,0,5);
          System.arraycopy(data.double0,0,SALInstance.double0,0,5);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] setArrays writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the setArrays command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setarrays>Test_setArrays</A>
  */
	public int acceptCommand_setArrays( Test.command_setArrays data )
	{
                command_setArrays_e11a567aSeqHolder SALInstance = new command_setArrays_e11a567aSeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setArrays_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_setArrays_e11a567aDataReader SALReader = command_setArrays_e11a567aDataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] setArrays reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";
           System.arraycopy(SALInstance.value[j].boolean0,0,data.boolean0,0,5);
           System.arraycopy(SALInstance.value[j].byte0,0,data.byte0,0,5);
           System.arraycopy(SALInstance.value[j].short0,0,data.short0,0,5);
           System.arraycopy(SALInstance.value[j].int0,0,data.int0,0,5);
           System.arraycopy(SALInstance.value[j].long0,0,data.long0,0,5);
           System.arraycopy(SALInstance.value[j].longLong0,0,data.longLong0,0,5);
           System.arraycopy(SALInstance.value[j].octet0,0,data.octet0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedShort0,0,data.unsignedShort0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedInt0,0,data.unsignedInt0,0,5);
           System.arraycopy(SALInstance.value[j].unsignedLong0,0,data.unsignedLong0,0,5);
           System.arraycopy(SALInstance.value[j].float0,0,data.float0,0,5);
           System.arraycopy(SALInstance.value[j].double0,0,data.double0,0,5);

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_setArrays( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_setArrays_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setArrays(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setArrays] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setArrays] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_setArrays( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setArrays(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_setArrays] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_setArrays(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_setArrays_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_setArrays] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_setArrays] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_setArrays( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setArrays_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_setArrays] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the fault command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#fault>Test_fault</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_fault( command_fault data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_fault_4b850e28 SALInstance = new command_fault_4b850e28();
          int status;
          int actorIdx = SAL__Test_command_fault_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_fault_4b850e28DataWriter SALWriter = command_fault_4b850e28DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "4b850e28";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] fault writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the fault command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#fault>Test_fault</A>
  */
	public int acceptCommand_fault( Test.command_fault data )
	{
                command_fault_4b850e28SeqHolder SALInstance = new command_fault_4b850e28SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_fault_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_fault_4b850e28DataReader SALReader = command_fault_4b850e28DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] fault reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_fault( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_fault_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_fault(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_fault] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_fault] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_fault( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_fault(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_fault] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_fault(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_fault_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_fault] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_fault] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_fault( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_fault_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_fault] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the wait command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#wait>Test_wait</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_wait( command_wait data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_wait_1b2e8a6f SALInstance = new command_wait_1b2e8a6f();
          int status;
          int actorIdx = SAL__Test_command_wait_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_wait_1b2e8a6fDataWriter SALWriter = command_wait_1b2e8a6fDataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "1b2e8a6f";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);
          SALInstance.ack = data.ack;
          SALInstance.duration = data.duration;

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] wait writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the wait command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#wait>Test_wait</A>
  */
	public int acceptCommand_wait( Test.command_wait data )
	{
                command_wait_1b2e8a6fSeqHolder SALInstance = new command_wait_1b2e8a6fSeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_wait_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_wait_1b2e8a6fDataReader SALReader = command_wait_1b2e8a6fDataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] wait reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";
           data.ack = SALInstance.value[j].ack;
           data.duration = SALInstance.value[j].duration;

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_wait( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_wait_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_wait(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_wait] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_wait] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_wait( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_wait(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_wait] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_wait(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_wait_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_wait] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_wait] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_wait( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_wait_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_wait] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the abort command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#abort>Test_abort</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_abort( command_abort data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_abort_1c6758a1 SALInstance = new command_abort_1c6758a1();
          int status;
          int actorIdx = SAL__Test_command_abort_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_abort_1c6758a1DataWriter SALWriter = command_abort_1c6758a1DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "1c6758a1";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] abort writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the abort command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#abort>Test_abort</A>
  */
	public int acceptCommand_abort( Test.command_abort data )
	{
                command_abort_1c6758a1SeqHolder SALInstance = new command_abort_1c6758a1SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_abort_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_abort_1c6758a1DataReader SALReader = command_abort_1c6758a1DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] abort reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_abort( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_abort_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_abort(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_abort] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_abort] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_abort( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_abort(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_abort] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_abort(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_abort_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_abort] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_abort] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_abort( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_abort_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_abort] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the enable command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#enable>Test_enable</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_enable( command_enable data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_enable_69193c6f SALInstance = new command_enable_69193c6f();
          int status;
          int actorIdx = SAL__Test_command_enable_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_enable_69193c6fDataWriter SALWriter = command_enable_69193c6fDataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "69193c6f";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] enable writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the enable command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#enable>Test_enable</A>
  */
	public int acceptCommand_enable( Test.command_enable data )
	{
                command_enable_69193c6fSeqHolder SALInstance = new command_enable_69193c6fSeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_enable_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_enable_69193c6fDataReader SALReader = command_enable_69193c6fDataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] enable reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_enable( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_enable_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_enable(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_enable] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_enable] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_enable( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_enable(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_enable] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_enable(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_enable_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_enable] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_enable] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_enable( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_enable_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_enable] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the disable command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#disable>Test_disable</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_disable( command_disable data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_disable_79c48b19 SALInstance = new command_disable_79c48b19();
          int status;
          int actorIdx = SAL__Test_command_disable_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_disable_79c48b19DataWriter SALWriter = command_disable_79c48b19DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "79c48b19";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] disable writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the disable command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#disable>Test_disable</A>
  */
	public int acceptCommand_disable( Test.command_disable data )
	{
                command_disable_79c48b19SeqHolder SALInstance = new command_disable_79c48b19SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_disable_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_disable_79c48b19DataReader SALReader = command_disable_79c48b19DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] disable reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_disable( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_disable_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_disable(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_disable] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_disable] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_disable( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_disable(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_disable] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_disable(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_disable_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_disable] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_disable] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_disable( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_disable_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_disable] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the standby command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#standby>Test_standby</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_standby( command_standby data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_standby_7054c900 SALInstance = new command_standby_7054c900();
          int status;
          int actorIdx = SAL__Test_command_standby_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_standby_7054c900DataWriter SALWriter = command_standby_7054c900DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "7054c900";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] standby writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the standby command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#standby>Test_standby</A>
  */
	public int acceptCommand_standby( Test.command_standby data )
	{
                command_standby_7054c900SeqHolder SALInstance = new command_standby_7054c900SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_standby_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_standby_7054c900DataReader SALReader = command_standby_7054c900DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] standby reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_standby( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_standby_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_standby(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_standby] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_standby] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_standby( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_standby(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_standby] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_standby(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_standby_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_standby] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_standby] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_standby( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_standby_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_standby] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the exitControl command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#exitcontrol>Test_exitControl</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_exitControl( command_exitControl data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_exitControl_9b992131 SALInstance = new command_exitControl_9b992131();
          int status;
          int actorIdx = SAL__Test_command_exitControl_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_exitControl_9b992131DataWriter SALWriter = command_exitControl_9b992131DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "9b992131";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] exitControl writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the exitControl command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#exitcontrol>Test_exitControl</A>
  */
	public int acceptCommand_exitControl( Test.command_exitControl data )
	{
                command_exitControl_9b992131SeqHolder SALInstance = new command_exitControl_9b992131SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_exitControl_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_exitControl_9b992131DataReader SALReader = command_exitControl_9b992131DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] exitControl reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_exitControl( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_exitControl_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_exitControl(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_exitControl] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_exitControl] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_exitControl( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_exitControl(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_exitControl] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_exitControl(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_exitControl_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_exitControl] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_exitControl] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_exitControl( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_exitControl_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_exitControl] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the start command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#start>Test_start</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_start( command_start data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_start_74351d61 SALInstance = new command_start_74351d61();
          int status;
          int actorIdx = SAL__Test_command_start_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_start_74351d61DataWriter SALWriter = command_start_74351d61DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "74351d61";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);
          SALInstance.settingsToApply = data.settingsToApply;

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] start writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the start command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#start>Test_start</A>
  */
	public int acceptCommand_start( Test.command_start data )
	{
                command_start_74351d61SeqHolder SALInstance = new command_start_74351d61SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_start_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_start_74351d61DataReader SALReader = command_start_74351d61DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] start reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";
           data.settingsToApply = SALInstance.value[j].settingsToApply;

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_start( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_start_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_start(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_start] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_start] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_start( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_start(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_start] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_start(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_start_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_start] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_start] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_start( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_start_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_start] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the enterControl command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#entercontrol>Test_enterControl</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_enterControl( command_enterControl data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_enterControl_abb0d976 SALInstance = new command_enterControl_abb0d976();
          int status;
          int actorIdx = SAL__Test_command_enterControl_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_enterControl_abb0d976DataWriter SALWriter = command_enterControl_abb0d976DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "abb0d976";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] enterControl writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the enterControl command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#entercontrol>Test_enterControl</A>
  */
	public int acceptCommand_enterControl( Test.command_enterControl data )
	{
                command_enterControl_abb0d976SeqHolder SALInstance = new command_enterControl_abb0d976SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_enterControl_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_enterControl_abb0d976DataReader SALReader = command_enterControl_abb0d976DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] enterControl reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_enterControl( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_enterControl_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_enterControl(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_enterControl] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_enterControl] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_enterControl( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_enterControl(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_enterControl] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_enterControl(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_enterControl_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_enterControl] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_enterControl] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_enterControl( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_enterControl_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_enterControl] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the setLogLevel command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setloglevel>Test_setLogLevel</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_setLogLevel( command_setLogLevel data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_setLogLevel_48c8505d SALInstance = new command_setLogLevel_48c8505d();
          int status;
          int actorIdx = SAL__Test_command_setLogLevel_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_setLogLevel_48c8505dDataWriter SALWriter = command_setLogLevel_48c8505dDataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "48c8505d";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);
          SALInstance.level = data.level;

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] setLogLevel writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the setLogLevel command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setloglevel>Test_setLogLevel</A>
  */
	public int acceptCommand_setLogLevel( Test.command_setLogLevel data )
	{
                command_setLogLevel_48c8505dSeqHolder SALInstance = new command_setLogLevel_48c8505dSeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setLogLevel_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_setLogLevel_48c8505dDataReader SALReader = command_setLogLevel_48c8505dDataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] setLogLevel reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";
           data.level = SALInstance.value[j].level;

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_setLogLevel( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_setLogLevel_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setLogLevel(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setLogLevel] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setLogLevel] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_setLogLevel( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setLogLevel(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_setLogLevel] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_setLogLevel(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_setLogLevel_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_setLogLevel] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_setLogLevel] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_setLogLevel( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setLogLevel_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_setLogLevel] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the setValue command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setvalue>Test_setValue</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_setValue( command_setValue data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_setValue_caab4364 SALInstance = new command_setValue_caab4364();
          int status;
          int actorIdx = SAL__Test_command_setValue_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_setValue_caab4364DataWriter SALWriter = command_setValue_caab4364DataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "caab4364";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);
          SALInstance.parametersAndValues = data.parametersAndValues;

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] setValue writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the setValue command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setvalue>Test_setValue</A>
  */
	public int acceptCommand_setValue( Test.command_setValue data )
	{
                command_setValue_caab4364SeqHolder SALInstance = new command_setValue_caab4364SeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setValue_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_setValue_caab4364DataReader SALReader = command_setValue_caab4364DataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] setValue reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";
           data.parametersAndValues = SALInstance.value[j].parametersAndValues;

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_setValue( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_setValue_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setValue(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setValue] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setValue] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_setValue( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setValue(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_setValue] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_setValue(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_setValue_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_setValue] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_setValue] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_setValue( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setValue_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_setValue] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Issue the setAuthList command to the Test subsystem
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setauthlist>Test_setAuthList</A>
  * @returns the sequence number aka command id
  */
	public int issueCommand_setAuthList( command_setAuthList data )
	{
          Random randGen = new java.util.Random();
  	  long cmdHandle = HANDLE_NIL.value;
          command_setAuthList_a648281d SALInstance = new command_setAuthList_a648281d();
          int status;
          int actorIdx = SAL__Test_command_setAuthList_ACTOR;
	  DataWriter dwriter = getWriter(actorIdx);	
	  command_setAuthList_a648281dDataWriter SALWriter = command_setAuthList_a648281dDataWriterHelper.narrow(dwriter);
	  SALInstance.private_revCode = "a648281d";
	  SALInstance.private_seqNum = sal[actorIdx].sndSeqNum;
          SALInstance.private_identity = CSC_identity;
          SALInstance.private_origin = origin;
          SALInstance.private_host = ddsIPaddress;
          SALInstance.private_sndStamp = getCurrentTime();
	  SALInstance.TestID = subsystemID;
	  cmdHandle = SALWriter.register_instance(SALInstance);
          SALInstance.authorizedUsers = data.authorizedUsers;
          SALInstance.nonAuthorizedCSCs = data.nonAuthorizedCSCs;

	  if (debugLevel > 0) {
	    System.out.println( "=== [issueCommand] setAuthList writing a command");
	  }
	  status = SALWriter.write(SALInstance, cmdHandle);
	  sal[actorIdx].sndSeqNum++;
	  return (sal[actorIdx].sndSeqNum-1);
	}


/** Accept the setAuthList command. The SAL will automatically generate an ackCmd message with an ack = SAL__CMD_ACK
  * @param data is the command payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#setauthlist>Test_setAuthList</A>
  */
	public int acceptCommand_setAuthList( Test.command_setAuthList data )
	{
                command_setAuthList_a648281dSeqHolder SALInstance = new command_setAuthList_a648281dSeqHolder();
                Test.ackcmd_902e1de4 ackdata;
   		SampleInfoSeqHolder info;
   		int status = 0;
                int j=0;
   		int istatus =  -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setAuthList_ACTOR;

  		// create DataWriter :
  		DataWriter dwriter = getWriter2(SAL__Test_ackcmd_ACTOR);
  		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
  		DataReader dreader = getReader(actorIdx);
  		command_setAuthList_a648281dDataReader SALReader = command_setAuthList_a648281dDataReaderHelper.narrow(dreader);
                info = new SampleInfoSeqHolder();
  		istatus = SALReader.take(SALInstance, info, 1, NOT_READ_SAMPLE_STATE.value, ANY_VIEW_STATE.value, ALIVE_INSTANCE_STATE.value);
		if (SALInstance.value.length > 0) {
   		  if (info.value[0].valid_data) {
    		     if (debugLevel > 8) {
      			System.out.println(  "=== [acceptCommand] setAuthList reading a command containing :" );
      			System.out.println(  "    seqNum   : " + SALInstance.value[0].private_seqNum );
    		    }
    		    status = SALInstance.value[0].private_seqNum;
    		    double rcvdTime = getCurrentTime();
		    double dTime = rcvdTime - SALInstance.value[0].private_sndStamp;
    		    if ( dTime < sal[actorIdx].sampleAge ) {
                      sal[actorIdx].activeorigin = SALInstance.value[0].private_origin;
                      sal[actorIdx].activehost = SALInstance.value[0].private_host;
                      sal[actorIdx].activeidentity = SALInstance.value[0].private_identity;
                      sal[actorIdx].activecmdid = SALInstance.value[0].private_seqNum;
                      ackdata = new Test.ackcmd_902e1de4();
	              ackdata.TestID = subsystemID;
		      ackdata.private_identity = SALInstance.value[0].private_identity;
		      ackdata.private_origin = SALInstance.value[0].private_origin;
		      ackdata.private_seqNum = SALInstance.value[0].private_seqNum;
		      ackdata.error  = 0;
		      ackdata.result = "SAL ACK";
           data.authorizedUsers = SALInstance.value[j].authorizedUsers;
           data.nonAuthorizedCSCs = SALInstance.value[j].nonAuthorizedCSCs;

		      status = SALInstance.value[0].private_seqNum;
		      rcvSeqNum = status;
		      rcvOrigin = SALInstance.value[0].private_origin;
		      rcvIdentity = SALInstance.value[0].private_identity;
		      ackdata.ack = SAL__CMD_ACK;
		      ackdata.TestID = subsystemID;
		      ackHandle = SALWriter.register_instance(ackdata);

		      istatus = SALWriter.write(ackdata, ackHandle);


    		     if (debugLevel > 8) {
      			System.out.println(  "    Old command ignored :   " + dTime );
                     }
                   }
		 }
                } else {
  	           status = 0;
                }
                SALReader.return_loan(SALInstance, info);
	        return status;
	}


/** Wait for the arrival of command ack. If no instance arrives before the timeout then return SAL__CMD_NOACK
  * else returns SAL__OK if a command message has been received.
  * @param cmdSeqNum is the sequence number of the command involved, as returned by issueCommand
  */
	public int waitForCompletion_setAuthList( int cmdSeqNum , int timeout )
	{
	   int status = 0;
           int actorIdx = SAL__Test_command_setAuthList_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status != SAL__CMD_COMPLETE && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setAuthList(ackcmd);
	      if (status != SAL__CMD_NOACK) {
	        if (sal[actorIdx].rcvSeqNum != cmdSeqNum) { 
	           status = SAL__CMD_NOACK;
	        }
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (status != SAL__CMD_COMPLETE) {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setAuthList] command " + cmdSeqNum +  " timed out");
	      } 
	      logError(status);
	   } else {
	      if (debugLevel > 0) {
	         System.out.println( "=== [waitForCompletion_setAuthList] command " + cmdSeqNum +  " completed ok");
	      } 
           }
 	   return status;
	}


	public int waitForAck_setAuthList( int timeout , ackcmd ack)
	{
	   int status = 0;
           int actorIdx = SAL__Test_ackcmd_ACTOR;
	   ackcmd_902e1de4SeqHolder ackcmd = new ackcmd_902e1de4SeqHolder();
           long finishBy = System.currentTimeMillis() + timeout*1000;

	   while (status == SAL__CMD_NOACK && System.currentTimeMillis() < finishBy ) {
	      status = getResponse_setAuthList(ackcmd);
	      if (status != SAL__CMD_NOACK) {
  		ack.private_seqNum = sal[actorIdx].rcvSeqNum;
   		ack.error = sal[actorIdx].error;
   		ack.ack = sal[actorIdx].ack;
   		ack.result = sal[actorIdx].result;
                ack.origin = sal[actorIdx].activeorigin;
                ack.host = sal[actorIdx].activehost;
                ack.identity = sal[actorIdx].activeidentity;
                ack.cmdtype = sal[actorIdx].activecmdid;
	      }
	      try
		{
	 	  Thread.sleep(1);
		}
		catch(InterruptedException ie)
		{
			// nothing to do
	      }
	   }
	   if (debugLevel > 0) {
	      System.out.println( "=== [waitForAck_setAuthList] ack " + status);
	   } 
 	   return status;
	}


/** Get the response (ack) from a command transaction. It is up to the application to validate against the 
  * command sequence number and command type if multiple commands may be in-progress simultaneously
  * @param data is the ackCmd payload
  * @returns SAL__CMD_NOACK if no ackCmd is available, or SAL__OK if there is
  */
	public int getResponse_setAuthList(ackcmd_902e1de4SeqHolder data)
	{
	  int status =  -1;
          int lastsample = 0;
          int actorIdx = SAL__Test_ackcmd_ACTOR;
          int actorIdxCmd = SAL__Test_command_setAuthList_ACTOR;

	  DataReader dreader = getReader2(actorIdx);
	  ackcmd_902e1de4DataReader SALReader = ackcmd_902e1de4DataReaderHelper.narrow(dreader);
  	  SampleInfoSeqHolder infoSeq = new SampleInfoSeqHolder();
	  SALReader.take(data, infoSeq, 1, 
					NOT_READ_SAMPLE_STATE.value,
					ANY_VIEW_STATE.value,
					ALIVE_INSTANCE_STATE.value);
	  if (data.value.length > 0) {
 		for (int i = 0; i < data.value.length; i++) {
                     if ( debugLevel > 8) {
				System.out.println("=== [getResponse_setAuthList] message received :");
				System.out.println("    revCode  : "
						+ data.value[i].private_revCode);
		    }
                    lastsample = i;
		}
	 	status = data.value[lastsample].ack;
	  	sal[actorIdxCmd].rcvOrigin = data.value[lastsample].private_origin;
	  	sal[actorIdxCmd].rcvSeqNum = data.value[lastsample].private_seqNum;
	  	sal[actorIdxCmd].rcvIdentity = data.value[lastsample].private_identity;
	  	sal[actorIdxCmd].activeorigin = data.value[lastsample].origin;
	  	sal[actorIdxCmd].activeidentity = data.value[lastsample].identity;
	  	sal[actorIdxCmd].activecmdid = data.value[lastsample].cmdtype;
	  	sal[actorIdxCmd].activehost = data.value[lastsample].host;
	  } else {
                if ( debugLevel > 8) {
	            System.out.println("=== [getResponse_setAuthList] No ack yet!"); 
                }
	        status = SAL__CMD_NOACK;
	  }
    	  SALReader.return_loan(data, infoSeq);
	  return status;
	}


/** Acknowledge a command by sending an ackCmd message, this time with access to all the ackCmd message payload
  * @param data is the ackCmd topic data
  */
	public int ackCommand_setAuthList( int cmdId, int ack, int error, String result )
	{
   		int istatus = -1;
   		long ackHandle = HANDLE_NIL.value;
                int actorIdx = SAL__Test_command_setAuthList_ACTOR;
                int actorIdx2 = SAL__Test_ackcmd_ACTOR;

   		Test.ackcmd_902e1de4 ackdata;
   		DataWriter dwriter = getWriter2(actorIdx2);
   		ackcmd_902e1de4DataWriter SALWriter = ackcmd_902e1de4DataWriterHelper.narrow(dwriter);
                ackdata = new Test.ackcmd_902e1de4();
   		ackdata.private_seqNum = cmdId;
   		ackdata.error = error;
   		ackdata.ack = ack;
                ackdata.origin = sal[actorIdx].activeorigin;
                ackdata.identity = sal[actorIdx].activeidentity;
                ackdata.host = sal[actorIdx].activehost;
                ackdata.private_host = ddsIPaddress;
                ackdata.private_origin = origin;
                ackdata.private_identity = CSC_identity;
   		ackdata.result = result;
   		ackdata.TestID = subsystemID;

   		if (debugLevel > 0) {
      			System.out.println(  "=== [ackCommand_setAuthList] acknowledging a command with :" );
      			System.out.println(  "    seqNum   : " + ackdata.private_seqNum );
      			System.out.println(  "    ack      : " + ackdata.ack );
      			System.out.println(  "    error    : " + ackdata.error );
      			System.out.println(  "    host     : " + ackdata.host );
      			System.out.println(  "    origin : " + ackdata.origin );
      			System.out.println(  "    identity : " + ackdata.identity );
      			System.out.println(  "    result   : " + ackdata.result );
   		}
   		ackdata.TestID = subsystemID;
   		ackHandle = SALWriter.register_instance(ackdata);
   		istatus = SALWriter.write(ackdata, ackHandle);

   		return SAL__OK;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_scalars</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_scalars(Test.logevent_scalars anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_scalars_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a scalars logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#scalars>Test_scalars</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_scalars( Test.logevent_scalars event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_scalars_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_arrays</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_arrays(Test.logevent_arrays anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_arrays_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a arrays logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#arrays>Test_arrays</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_arrays( Test.logevent_arrays event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_arrays_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingversions>Test_settingVersions</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_settingVersions(Test.logevent_settingVersions anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_settingVersions_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a settingVersions logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingversions>Test_settingVersions</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_settingVersions( Test.logevent_settingVersions event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_settingVersions_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#errorcode>Test_errorCode</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_errorCode(Test.logevent_errorCode anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_errorCode_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a errorCode logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#errorcode>Test_errorCode</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_errorCode( Test.logevent_errorCode event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_errorCode_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#summarystate>Test_summaryState</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_summaryState(Test.logevent_summaryState anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_summaryState_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a summaryState logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#summarystate>Test_summaryState</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_summaryState( Test.logevent_summaryState event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_summaryState_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#appliedsettingsmatchstart>Test_appliedSettingsMatchStart</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_appliedSettingsMatchStart(Test.logevent_appliedSettingsMatchStart anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_appliedSettingsMatchStart_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a appliedSettingsMatchStart logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#appliedsettingsmatchstart>Test_appliedSettingsMatchStart</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_appliedSettingsMatchStart( Test.logevent_appliedSettingsMatchStart event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_appliedSettingsMatchStart_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#loglevel>Test_logLevel</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_logLevel(Test.logevent_logLevel anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_logLevel_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a logLevel logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#loglevel>Test_logLevel</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_logLevel( Test.logevent_logLevel event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_logLevel_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#logmessage>Test_logMessage</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_logMessage(Test.logevent_logMessage anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_logMessage_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a logMessage logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#logmessage>Test_logMessage</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_logMessage( Test.logevent_logMessage event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_logMessage_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingsapplied>Test_settingsApplied</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_settingsApplied(Test.logevent_settingsApplied anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_settingsApplied_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a settingsApplied logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#settingsapplied>Test_settingsApplied</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_settingsApplied( Test.logevent_settingsApplied event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_settingsApplied_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#simulationmode>Test_simulationMode</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_simulationMode(Test.logevent_simulationMode anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_simulationMode_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a simulationMode logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#simulationmode>Test_simulationMode</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_simulationMode( Test.logevent_simulationMode event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_simulationMode_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#softwareversions>Test_softwareVersions</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_softwareVersions(Test.logevent_softwareVersions anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_softwareVersions_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a softwareVersions logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#softwareversions>Test_softwareVersions</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_softwareVersions( Test.logevent_softwareVersions event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_softwareVersions_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#heartbeat>Test_heartbeat</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_heartbeat(Test.logevent_heartbeat anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_heartbeat_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a heartbeat logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#heartbeat>Test_heartbeat</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_heartbeat( Test.logevent_heartbeat event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_heartbeat_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}


/** Receive a logevent message.
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#authlist>Test_authList</A>
  * @returns SAL__NO_UPDATES if no data is available, or SAL__OK otherwise
  */
	public int getEvent_authList(Test.logevent_authList anEvent)
	{
	  int status =  -1;
          int actorIdx = SAL__Test_logevent_authList_ACTOR;
          if (sal[actorIdx].subscriber == null) {
             createSubscriber(actorIdx);
             createReader(actorIdx,false);
             sal[actorIdx].isEventReader = true;
          }
          int maxSample = sal[actorIdx].maxSamples;
          sal[actorIdx].maxSamples=1;
          status = getSample(anEvent);
          sal[actorIdx].maxSamples=maxSample;
	  return status;
	}

/** Publish a authList logevent message
  * @param data is the logevent payload <A HREF=https://ts-xml.lsst.io/sal_interfaces/Test.html#authlist>Test_authList</A>
  * @priority is a user configurable priority, larger is higher priority
  */
	public int logEvent_authList( Test.logevent_authList event, int priority )
	{
	   int status = 0;
           int actorIdx = SAL__Test_logevent_authList_ACTOR;
           event.priority=priority;
           if (sal[actorIdx].publisher == null) {
              createPublisher(actorIdx);
              boolean autodispose_unregistered_instances = true;
              createWriter(actorIdx,autodispose_unregistered_instances);
              sal[actorIdx].isEventWriter = true;
           }
           status = putSample(event);
           return status;
	}



        public int getActorIndex (String topicName) {
          for (int i=0; i<SAL__ACTORS_MAXCOUNT;i++) {
             if ( topicName.length() == sal[i].topicName.length() )  {
                if ( topicName.equals(sal[i].topicName) )  {
                   return i;
                }
             }
          }
          return SAL__ERROR;
        }


/** Sets up the DDS Publisher support for the named DDS type
  @param topicName is the name of the DDS topic
  @throws "Unknown Topic" if the topic is not defined
 */    
	public int salTelemetryPub(String topicName)
	{
	  int actorIdx = -1;
	  int status = 0;

	  actorIdx = getActorIndex(topicName);
	  if (actorIdx > -1) {
	     salTelemetryPub(actorIdx);
	  } else {
	     status = SAL__ERROR;
	  }
	  return status;
	}

/** Sets up the DDS Subscriber support for the named DDS type
  @param topicName is the name of the DDS topic
  @throws "Unknown Topic" if the topic is not defined
 */    
	public int salTelemetrySub(String topicName)
	{
	  int actorIdx = -1;
	  int status = 0;

	  actorIdx = getActorIndex(topicName);
	  if (actorIdx > -1) {
	     salTelemetrySub(actorIdx);
	  } else {
	     status = SAL__ERROR;
	  }
	  return status;
	}


/** Sets up the DDS Publisher support for the indexed SAL Actor
  @param actorIdx is the index of the DDS topic's salActor
  @throws "Unknown Topic" if the topic is not defined
 */    
        public int salTelemetryPub(int actorIdx)
	{
		String partitionName = domainName;

		// create Domain Participant
		createParticipant(partitionName);

		// create Type
		salTypeSupport(actorIdx);

		// create Topic
		createTopic(actorIdx);

		// create Publisher
		createPublisher(actorIdx);

		// create DataWriter
		createWriter(actorIdx,true);
		sal[actorIdx].isWriter = true;
                return SAL__OK;
        }




/** Sets up the DDS Subscriber support for the indexed SAL Actor
  @param actorIdx is the index of the DDS topic's salActor
  @throws "Unknown Topic" if the topic is not defined
 */    
        public int salTelemetrySub(int actorIdx)
	{
		String partitionName = domainName;
		// create Domain Participant
		createParticipant(partitionName);

		// create Type
		salTypeSupport(actorIdx);
		// create Type

		// create Topic
		createTopic(actorIdx);

		// create Subscriber
		createSubscriber(actorIdx);

		// create DataReader
  		// Filter expr
                String expr[] = new String[0];
                String sFilter = "TestID = " + subsystemID;
                String ftopicName = "filtered_" + sal[actorIdx].topicHandle;
   		createContentFilteredTopic(actorIdx,ftopicName, sFilter, expr);

		// create DataReader
 		createReader(actorIdx,true);
                sal[actorIdx].isReader = true;
                return SAL__OK;
	}



/// Placeholder routine to be replaced by system wide logging once available
	public void logError(int status) 
	{
		System.out.println("=== ERROR return value = " + status); 
	}

/** Constructor for the SAL_Test object.
  *
  @param aKey is used to specify the index of an indexed component, or 0 for non-indexed ones
  @param identity is used to specify the private_identity of a commander

 */
        
        public SAL_Test(int aKey, String identity)
	{
                CSC_identity = String.format("%s" , identity);
		initSalEnvironment(aKey);
	}

        public SAL_Test(int aKey)
	{
                CSC_identity = String.format("Test:%d" , aKey);
		initSalEnvironment(aKey);
	}

/** Environment setup for a SAL_Test object.
  *
  * + LSST_DDS_PARTITION_PREFIX is the name of the partition being used
  * + LSST_DDS_IP is the IP address of the DDS ethernet interface
  * + LSST_DDS_QOS is the optional path to the DDS QoS XML description
  * + LSST_DDS_HISTORYSYNC is the maximum number of seconds to wait to obtain historical messages on a topic
  *
  @param aKey is used to specify the index of an indexed component, or 0 for non-indexed ones
 */
        public void initSalEnvironment(int aKey)
	{
		Random randGen = new java.util.Random();
                String pname = System.getenv("LSST_DDS_PARTITION_PREFIX");
                String ipaddress = System.getenv("LSST_DDS_IP");
                String hname = System.getenv("LSST_DDS_HISTORYSYNC");
                String qname = System.getenv("LSST_DDS_QOS");
                if (pname != null) {
                   partitionPrefix = pname;
                } else {
                   System.out.println("=== WARNING : LSST_DDS_PARTITION_PREFIX not defined\n");
                }
		if (ipaddress == null) {
		  ddsIPaddress = (int)randGen.nextInt(99999999);
                } else {
                  int ipasint = 0;
                  String[] ipAddressinArray = ipaddress.split("\\.");
		  for (int i=0;i<ipAddressinArray.length;i++) {
		     int power = 3-i;
		     int ip = Integer.parseInt(ipAddressinArray[i]);
                     ipasint += ip * Math.pow(256,power);
                  }
		  ddsIPaddress = ipasint;
                }
                if (hname == null) {
                   historySync = 0;
                } else {
                   historySync = Integer.parseInt(hname);
                }
                if (qname != null) {
                  commandQos = new QosProvider(qname,"CommandProfile");
                  eventQos = new QosProvider(qname,"EventProfile");
                  telemetryQos = new QosProvider(qname,"TelemetryProfile");
                  ackcmdQos = new QosProvider(qname,"AckcmdProfile");
                }
                CSC_identity = "Test";
                origin = (int)randGen.nextInt(99999999);
		hasReader = false;
		hasWriter = false;
		hasCommand = false;
		hasEventReader = false;
		hasEventWriter = false;
		hasProcessor = false;
		subsystemID = 0;
		debugLevel = 0;
                System.loadLibrary("salUtils");
                initSalActors();
	}


/** Get the time from the system. The TAI time will be used, which assumes that the kernel 
  * leap seconds offset has been correctly set. The getLeapSeconds method can be used to 
  * do a simple sanity check
  @return The current TAI system time
 */
	public double getCurrentTime()
	{
  		double ts = salUtil.getTAISeconds();
		return ts;
	}



// INSERT CMDALIAS SUPPORT  for issueCommand and acceptCommand  



	public int cancelCommand( int cmdSeqNum )
	{
	   int status = 0;
	   return status;
	}


	public int abortCommand( int cmdSeqNum )
	{
	   int status = 0;
	   return status;
	}

        public static final int SAL__SLOWPOLL= 		   1;
        public static final int SAL__OK = 		   0;
        public static final int SAL__ERR = 		  -1;
        public static final int SAL__ERROR = 		  -1;
        public static final int SAL__NO_UPDATES = 	-100;
        public static final int SAL__LOG_ROUTINES = 	   1;
        public static final int SAL__CMD_ACK =      	 300;
        public static final int SAL__CMD_INPROGRESS = 	 301;
        public static final int SAL__CMD_STALLED =    	 302;
        public static final int SAL__CMD_COMPLETE =   	 303;
        public static final int SAL__CMD_NOPERM =   	-300;
        public static final int SAL__CMD_NOACK =    	-301;
        public static final int SAL__CMD_FAILED =   	-302;
        public static final int SAL__CMD_ABORTED =  	-303;
        public static final int SAL__CMD_TIMEOUT =  	-304;
        public static final int SAL__DATA_AVAIL	=	400;
        public static final int SAL__DEADLINE_MISS =	401;
        public static final int SAL__INCOMPAT_QOS =	402;
        public static final int SAL__SAMPLE_REJ	=	403;
        public static final int SAL__LIVELINESS_CHG =	404;
        public static final int SAL__SAMPLELOST	=	405;
        public static final int SAL__SUBSCR_MATCH =	406;
        public static final int SAL__STATE_DISABLED = 	1;
        public static final int SAL__STATE_ENABLED = 	2;
        public static final int SAL__STATE_FAULT = 	3;
        public static final int SAL__STATE_OFFLINE = 	4;
        public static final int SAL__STATE_STANDBY = 	5;
        public static final int SAL__STATE_COUNT = 	5;





// INSERT EVENTALIAS SUPPORT



/// Set the debug message output verbosity level for this SAL_Test object
	public int setDebugLevel( int level )
	{
           int status = 0;
	   debugLevel = level;
	   return status;	
	}

/// Get the current value of the debug message verbosity level for this SAL_Test object
	public int getDebugLevel( int level )
	{
	   return debugLevel;
	}

/// Get the current IP address
	public int getOrigin()
	{
	   int status = 0;
	   return status;
	}

/// Generic method to read the value of a SAL_Test object internal state
	public int getProperty(String property, String value)
	{
	   int status = SAL__OK;
	   value = "UNKNOWN";
	   if (status != SAL__OK) {
	      if (debugLevel >= SAL__LOG_ROUTINES) {
	          logError(status);
	      }
	   }
	   return status;
	}

/// Generic method to set the value of a SAL_Test object internal state
	public int setProperty(String property, String value)
	{
           int status = SAL__OK;
	   if (status != SAL__OK) {
	      if (debugLevel >= SAL__LOG_ROUTINES) {
	          logError(status);
	      }
	   }
	   return status;
	}
 

/// Close down the DDS support for this SAL_Test object
	public void salShutdown()
	{
          if (participant != null) {
            participant.delete_contained_entities();
  	    deleteParticipant();
          }
	}


/** Sets up the DDS Subscriber support for the named DDS event topic
  @param topicName is the name of the DDS topic
  @throws "Unknown Topic" if the topic is not defined
 */    
	public int salEventSub(String topicName)
	{
		int status = SAL__ERROR;
                int actorIdx = getActorIndex(topicName);
                if ( actorIdx < 0) {return SAL__ERROR;}
		status = salTelemetrySub(actorIdx);
                sal[actorIdx].isEventReader = true;
		return status;
	}

/** Sets up the DDS Publisher support for the named DDS event topic
  @param topicName is the name of the DDS topic
  @throws "Unknown Topic" if the topic is not defined
 */    
	public int salEventPub(String topicName)
	{
		int status = SAL__ERROR;
                int actorIdx = getActorIndex(topicName);
                if ( actorIdx < 0) {return SAL__ERROR;}
		status = salTelemetryPub(actorIdx);
                sal[actorIdx].isEventWriter = true;
		return status;
	}

/** Sets up the DDS Participant and specify a DDS partition for it
  @param partition is the partition specifier of the DDS topic
 */    
	public void createParticipant(String partitionName) {
           if (participant == null) {
		dpf = DomainParticipantFactory.get_instance();
		checkHandle(dpf, "DomainParticipantFactory.get_instance");

		participant = dpf.create_participant(DOMAIN_ID_DEFAULT.value,
				PARTICIPANT_QOS_DEFAULT.value, null, STATUS_MASK_NONE.value);
		checkHandle(dpf,
				"DomainParticipantFactory.create_participant");
		this.partitionName = partitionName;
           }
	}

/** Deletes the DDS Participant
 */    
	public void deleteParticipant() {
		dpf.delete_participant(participant);
	}

	public void registerType(TypeSupportImpl ts) {
		typeName = ts.get_type_name();
		int status = ts.register_type(participant, typeName);
		checkStatus(status, "register_type");
	}

	public void registerType2(TypeSupportImpl ts) {
		typeName2 = ts.get_type_name();
		int status = ts.register_type(participant, typeName2);
		checkStatus(status, "register_type");
	}

	public void registerType(int actorIdx, TypeSupportImpl ts) {
		sal[actorIdx].typeName = ts.get_type_name();
		int status = ts.register_type(participant, sal[actorIdx].typeName);
		checkStatus(status, "register_type");
	}

	public void registerType2(int actorIdx,TypeSupportImpl ts) {
		sal[actorIdx].typeName2 = ts.get_type_name();
		int status = ts.register_type(participant, sal[actorIdx].typeName2);
		checkStatus(status, "register_type");
	}


	public void createTopic(int actorIdx) {
		int status = -1;
                if (debugLevel > 1) {
  		  System.out.println("=== [createTopic] : topicName " + sal[actorIdx].topicName + " type = " + sal[actorIdx].typeName);
                }
		sal[actorIdx].topic = participant.create_topic(sal[actorIdx].topicHandle, sal[actorIdx].typeName, sal[actorIdx].topicQos.value,
				null, STATUS_MASK_NONE.value);
		checkHandle(sal[actorIdx].topic, "DomainParticipant.create_topic");
	}

	public void createTopic2(int actorIdx) {
		int status = -1;
                if (debugLevel > 1) {
		  System.out.println("=== [createTopic2] : topicName " + sal[actorIdx].topicName + " type = " + sal[actorIdx].typeName2);
                }
		sal[actorIdx].topic2 = participant.create_topic(sal[actorIdx].topicHandle, sal[actorIdx].typeName2, sal[actorIdx].topicQos2.value,
				null, STATUS_MASK_NONE.value);
		checkHandle(sal[actorIdx].topic2, "DomainParticipant.create_topic");
	}

	public void createTopic(int actorIdx, String topicName) {
		int status = -1;
                if (debugLevel > 1) {
		  System.out.println("=== [createTopic] : topicName " + topicName + " type = " + sal[actorIdx].typeName);
                }
		sal[actorIdx].topic = participant.create_topic(sal[actorIdx].topicHandle, sal[actorIdx].typeName, sal[actorIdx].topicQos.value,
				null, STATUS_MASK_NONE.value);
		checkHandle(sal[actorIdx].topic, "DomainParticipant.create_topic");
	}

	public void createTopic2(int actorIdx, String topicName) {
		int status = -1;
                if (debugLevel > 1) {
		  System.out.println("=== [createTopic2] : topicName " + topicName + " type = " + sal[actorIdx].typeName2);
                }

		sal[actorIdx].topic2 = participant.create_topic(sal[actorIdx].topicHandle, sal[actorIdx].typeName2, sal[actorIdx].topicQos2.value,
				null, STATUS_MASK_NONE.value);
		checkHandle(sal[actorIdx].topic2, "DomainParticipant.create_topic");
	}



        public void  createContentFilteredTopic( String topicName, String filter, String[] expr)
	{
	  filteredtopic = participant.create_contentfilteredtopic(topicName,topic, filter, expr);
	  checkHandle(filteredtopic, "DomainParticipant::create_contentfilteredtopic");
	}

        public void  createContentFilteredTopic2( String topicName, String filter, String[] expr)
	{
	  filteredtopic2 = participant.create_contentfilteredtopic(topicName,topic2, filter, expr);
	  checkHandle(filteredtopic2, "DomainParticipant::create_contentfilteredtopic");
	}



        public void  createContentFilteredTopic(int actorIdx, String topicName, String filter, String[] expr)
	{
	  sal[actorIdx].filteredtopic = participant.create_contentfilteredtopic(topicName,sal[actorIdx].topic, filter, expr);
	  checkHandle(sal[actorIdx].filteredtopic, "DomainParticipant::create_contentfilteredtopic");
	}

        public void  createContentFilteredTopic2(int actorIdx,  String topicName, String filter, String[] expr)
	{
	  sal[actorIdx].filteredtopic2 = participant.create_contentfilteredtopic(topicName,sal[actorIdx].topic2, filter, expr);
	  checkHandle(sal[actorIdx].filteredtopic2, "DomainParticipant::create_contentfilteredtopic");
	}



	public void deleteTopics() {
            if (filteredtopic != null) {
		int status = participant.delete_contentfilteredtopic(filteredtopic);
		checkStatus(status, "DDS.DomainParticipant.delete_contentfilteredtopic");
            }
            if (filteredtopic2 != null) {
		int status = participant.delete_contentfilteredtopic(filteredtopic2);
		checkStatus(status, "DDS.DomainParticipant.delete_contentfilteredtopic");
            }
            if (topic != null) {
		int status = participant.delete_topic(topic);
		checkStatus(status, "DDS.DomainParticipant.delete_topic");
            }
            if (topic2 != null) {
		int status = participant.delete_topic(topic2);
		checkStatus(status, "DDS.DomainParticipant.delete_topic");
            }
            for (int i=0;  i<SAL__ACTORS_MAXCOUNT; i++) {
             if (sal[i] != null) {
              if (sal[i].filteredtopic != null) {
  		  int status = participant.delete_contentfilteredtopic(sal[i].filteredtopic);
  		  checkStatus(status, "DDS.DomainParticipant.delete_contentfilteredtopic");
              }
              if (sal[i].filteredtopic2 != null) {
		  int status = participant.delete_contentfilteredtopic(sal[i].filteredtopic2);
		  checkStatus(status, "DDS.DomainParticipant.delete_contentfilteredtopic");
              }
              if (sal[i].topic != null) {
		  int status = participant.delete_topic(sal[i].topic);
		  checkStatus(status, "DDS.DomainParticipant.delete_topic");
              }
              if (sal[i].topic2 != null) {
		  int status = participant.delete_topic(sal[i].topic2);
		  checkStatus(status, "DDS.DomainParticipant.delete_topic");
              }
             }
            }
	}


	public void createPublisher(int actorIdx) {
	    sal[actorIdx].pubQos.value.partition.name = new String[1];
	    sal[actorIdx].pubQos.value.partition.name[0] = sal[actorIdx].partition;
	    sal[actorIdx].publisher = participant.create_publisher(sal[actorIdx].pubQos.value, null,
				STATUS_MASK_NONE.value);
	    checkHandle(sal[actorIdx].publisher,
				"DomainParticipant.create_publisher");
	}


	public void deletePublisher() {
            if (publisher != null) {
		participant.delete_publisher(publisher);
            }
            for (int i=0;  i<SAL__ACTORS_MAXCOUNT; i++) {
                   if (sal[i] != null) {
                      if (sal[i].publisher != null) {
		         participant.delete_publisher(sal[i].publisher);
                      }
                   }
            }
	}

	public void deleteReaders() {
            if (reader != null) {
		subscriber.delete_datareader(reader);
            }
            if (reader2 != null) {
		subscriber.delete_datareader(reader2);
            }
            for (int i=0;  i<SAL__ACTORS_MAXCOUNT; i++) {
               if (sal[i] != null) {
                 if (sal[i].reader != null) {
	   	   sal[i].subscriber.delete_datareader(sal[i].reader);
                 }
                 if (sal[i].reader2 != null) {
		   sal[i].subscriber.delete_datareader(sal[i].reader2);
                 }
               }
            }
	}

	public void deleteWriters() {
            if (writer != null) {
		publisher.delete_datawriter(writer);
            }
            if (writer2 != null) {
		publisher.delete_datawriter(writer2);
            }
            for (int i=0;  i<SAL__ACTORS_MAXCOUNT; i++) {
               if (sal[i] != null) {
                 if (sal[i].writer != null) {
		   sal[i].publisher.delete_datawriter(sal[i].writer);
                 }
                 if (sal[i].writer2 != null) {
		   sal[i].publisher.delete_datawriter(sal[i].writer2);
                 }
               }
            }
	}



	public void createWriter(boolean autodispose) {
	    publisher.get_default_datawriter_qos(WQosH);
	    publisher.copy_from_topic_qos(WQosH, topicQos.value);
	    WQosH.value.writer_data_lifecycle.autodispose_unregistered_instances = autodispose;
	    writer = publisher.create_datawriter(topic, WQosH.value, null,STATUS_MASK_NONE.value);
	    checkHandle(writer, "Publisher.create_datawriter");
	}
        
	public void createWriter2(boolean autodispose) {
	    publisher.get_default_datawriter_qos(WQosH);
	    publisher.copy_from_topic_qos(WQosH, topicQos.value);
	    WQosH.value.writer_data_lifecycle.autodispose_unregistered_instances = autodispose;
	    writer2 = publisher.create_datawriter(topic2, WQosH.value, null,STATUS_MASK_NONE.value);
	    checkHandle(writer2, "Publisher.create_datawriter");
	}


	public void createWriter(int actorIdx, boolean autodispose) {
 	    sal[actorIdx].WQosH.value.writer_data_lifecycle.autodispose_unregistered_instances = autodispose;
	    sal[actorIdx].writer = sal[actorIdx].publisher.create_datawriter(sal[actorIdx].topic, sal[actorIdx].WQosH.value, null,STATUS_MASK_NONE.value);
	    checkHandle(sal[actorIdx].writer, "Publisher.create_datawriter");
            if (debugLevel > 1) {
		  System.out.println("=== [createwriter idx] : topic " + sal[actorIdx].topic + " writer = " + sal[actorIdx].writer);
            }
            sal[actorIdx].isWriter = true;
	}
        
	public void createWriter2(int actorIdx, boolean autodispose) {
	    sal[actorIdx].WQosH.value.writer_data_lifecycle.autodispose_unregistered_instances = autodispose;
	    sal[actorIdx].writer2 = sal[actorIdx].publisher.create_datawriter(sal[actorIdx].topic2, sal[actorIdx].WQosH.value, null,STATUS_MASK_NONE.value);
	    checkHandle(sal[actorIdx].writer2, "Publisher.create_datawriter");
            if (debugLevel > 1) {
		  System.out.println("=== [createwriter2 idx] : topic " + sal[actorIdx].topic2 + " writer = " + sal[actorIdx].writer2);
            }
            sal[actorIdx].isWriter = true;
	}


	public void createSubscriber(int actorIdx) {
	    sal[actorIdx].subQos.value.partition.name = new String[1];
	    sal[actorIdx].subQos.value.partition.name[0] = sal[actorIdx].partition;
	    sal[actorIdx].subscriber = participant.create_subscriber(sal[actorIdx].subQos.value, null,
				STATUS_MASK_NONE.value);
	    checkHandle(sal[actorIdx].subscriber,
				"DomainParticipant.create_subscriber");
	}



	public void deleteSubscriber() {
                if (subscriber != null) {
  		   participant.delete_subscriber(subscriber);
                }
                for (int i=0;  i<SAL__ACTORS_MAXCOUNT; i++) {
                   if (sal[i] != null) {
                      if (sal[i].subscriber != null) {
		         participant.delete_subscriber(sal[i].subscriber);
                      }
                   }
                }
	}



	public void createReader(boolean filtered) {
	  if (filtered) {
	  	 reader = subscriber.create_datareader(filteredtopic,
		   	 DATAREADER_QOS_USE_TOPIC_QOS.value, null, STATUS_MASK_NONE.value);
	  } else {
		reader = subscriber.create_datareader(topic,
			DATAREADER_QOS_USE_TOPIC_QOS.value, null, STATUS_MASK_NONE.value);
	  }
	  checkHandle(reader, "Subscriber.create_datareader");
	}

	public void createReader2(boolean filtered) {
	  if (filtered) {
	  	 reader2 = subscriber.create_datareader(filteredtopic2,
		   	 DATAREADER_QOS_USE_TOPIC_QOS.value, null, STATUS_MASK_NONE.value);
	  } else {
		reader2 = subscriber.create_datareader(topic2,
			DATAREADER_QOS_USE_TOPIC_QOS.value, null, STATUS_MASK_NONE.value);
	  }
	  checkHandle(reader2, "Subscriber.create_datareader");
	}


	public void createReader(int actorIdx, boolean filtered) {
	  if (filtered) {
	  	 sal[actorIdx].reader = sal[actorIdx].subscriber.create_datareader(sal[actorIdx].filteredtopic,
		   	sal[actorIdx].RQosH.value, null, STATUS_MASK_NONE.value);
                if (debugLevel > 1) {
		  System.out.println("=== [createreader idx] : topic " + sal[actorIdx].filteredtopic + " reader = " + sal[actorIdx].reader);
                }
	  } else {
		sal[actorIdx].reader = sal[actorIdx].subscriber.create_datareader(sal[actorIdx].topic,
			sal[actorIdx].RQosH.value, null, STATUS_MASK_NONE.value);
                if (debugLevel > 1) {
		  System.out.println("=== [createreader idx] : topic " + sal[actorIdx].topic + " reader = " + sal[actorIdx].reader);
                }
	  }
	  checkHandle(sal[actorIdx].reader, "Subscriber.create_datareader");
          if ( (sal[actorIdx].topicQos.value.durability.kind != DurabilityQosPolicyKind.VOLATILE_DURABILITY_QOS) && (historySync > 0) ) {
	    DDS.Duration_t a_timeout = new Duration_t();
	    a_timeout.sec = historySync;
	    a_timeout.nanosec = 10000000;
	    sal[actorIdx].reader.wait_for_historical_data(a_timeout);
          }
          sal[actorIdx].sampleAge = 100.0;
          sal[actorIdx].isReader = true;
	}

	public void createReader2(int actorIdx,boolean filtered) {
	  if (filtered) {
	   	sal[actorIdx].reader2 = sal[actorIdx].subscriber.create_datareader(sal[actorIdx].filteredtopic2,
		   	 sal[actorIdx].RQosH.value, null, STATUS_MASK_NONE.value);
                if (debugLevel > 1) {
		  System.out.println("=== [createreader2 idx] : topic " + sal[actorIdx].filteredtopic2 + " reader = " + sal[actorIdx].reader2);
                }
	  } else {
		sal[actorIdx].reader2 = sal[actorIdx].subscriber.create_datareader(sal[actorIdx].topic2,
			sal[actorIdx].RQosH.value, null, STATUS_MASK_NONE.value);
                if (debugLevel > 1) {
		  System.out.println("=== [createreader2 idx] : topic " + sal[actorIdx].topic2 + " reader = " + sal[actorIdx].reader2);
                }
	  }
	  checkHandle(sal[actorIdx].reader2, "Subscriber.create_datareader");
          if ( (sal[actorIdx].topicQos2.value.durability.kind != DurabilityQosPolicyKind.VOLATILE_DURABILITY_QOS) && (historySync > 0) ) {
  	    DDS.Duration_t a_timeout = new Duration_t();
	    a_timeout.sec = historySync;
	    a_timeout.nanosec = 10000000;
	    sal[actorIdx].reader2.wait_for_historical_data(a_timeout);
          }
          sal[actorIdx].sampleAge = 100.0;
          sal[actorIdx].isReader = true;
	}

	public DataReader getReader() {
		return reader;
	}

	public DataReader getReader2() {
		return reader2;
	}

	public DataWriter getWriter() {
		return writer;
	}

	public DataWriter getWriter2() {
		return writer2;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public Topic getTopic() {
		return topic;
	}

	public Topic getTopic2() {
		return topic2;
	}



	public DataReader getReader(int actorIdx) {
		return sal[actorIdx].reader;
	}

	public DataReader getReader2(int actorIdx) {
		return sal[actorIdx].reader2;
	}

	public DataWriter getWriter(int actorIdx) {
		return sal[actorIdx].writer;
	}

	public DataWriter getWriter2(int actorIdx) {
		return sal[actorIdx].writer2;
	}

	public Publisher getPublisher(int actorIdx) {
		return sal[actorIdx].publisher;
	}

	public Subscriber getSubscriber(int actorIdx) {
		return sal[actorIdx].subscriber;
	}

	public Topic getTopic(int actorIdx) {
		return sal[actorIdx].topic;
	}

	public Topic getTopic2(int actorIdx) {
		return sal[actorIdx].topic2;
	}

	public DomainParticipant getParticipant() {
		return participant;
	}

	public static final int NR_ERROR_CODES = 13;

	/* Array to hold the names for all ReturnCodes. */
	public static String[] RetCodeName = new String[NR_ERROR_CODES];

	static {
		RetCodeName[0] = new String("DDS_RETCODE_OK");
		RetCodeName[1] = new String("DDS_RETCODE_ERROR");
		RetCodeName[2] = new String("DDS_RETCODE_UNSUPPORTED");
		RetCodeName[3] = new String("DDS_RETCODE_BAD_PARAMETER");
		RetCodeName[4] = new String("DDS_RETCODE_PRECONDITION_NOT_MET");
		RetCodeName[5] = new String("DDS_RETCODE_OUT_OF_RESOURCES");
		RetCodeName[6] = new String("DDS_RETCODE_NOT_ENABLED");
		RetCodeName[7] = new String("DDS_RETCODE_IMMUTABLE_POLICY");
		RetCodeName[8] = new String("DDS_RETCODE_INCONSISTENT_POLICY");
		RetCodeName[9] = new String("DDS_RETCODE_ALREADY_DELETED");
		RetCodeName[10] = new String("DDS_RETCODE_TIMEOUT");
		RetCodeName[11] = new String("DDS_RETCODE_NO_DATA");
		RetCodeName[12] = new String("DDS_RETCODE_ILLEGAL_OPERATION");
	}

	/*
	 * Returns the name of an error code.
	 */
	public static String getErrorName(int status) {
		return RetCodeName[status];
	}

	/*
	 * Check the return status for errors. If there is an error, then terminate.
	 */
	public static void checkStatus(int status, String info) {
		if (status != RETCODE_OK.value && status != RETCODE_NO_DATA.value) {
			System.out
					.println("Error in " + info + ": " + getErrorName
(status));
			System.exit(-1);
		}
	}

	/*
	 * Check whether a valid handle has been returned. If not, then terminate.
	 */
	public static void checkHandle(Object handle, String info) {
		if (handle == null) {
			System.out.println("Error in " + info
					+ ": Creation failed: invalid handle");
			System.exit(-1);
		}
	}

}

