package com.neeldeshmukh.vpn.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> faq1 = new ArrayList<String>();
        faq1.add("A Virtual Private Network is an online service that encrypts and tunnels your Internet traffic through its server. With a VPN:\n" +
                "\n" +
                "Your Internet Service Provider can’t see what you’re doing online.\n" +
                "You can spoof your location, i.e., appear as if connecting from another geographical location.\n" +
                "You can bypass state censorship and access websites and services banned in your country.");

        List<String> faq2 = new ArrayList<String>();
        faq2.add("It depends on your threat scenario. If you want to access streaming and gaming services like Netflix, Hulu, or Steam US libraries, a VPN alone is enough. Likewise, if you use public Wi-Fi frequently, you’re safe with a reliable provider.\n" +
                "\n" +
                "On the other hand, if you need to avoid state surveillance, you shouldn’t rely on a single privacy tool to protect you. ");
        List<String> faq3 = new ArrayList<String>();
        faq3.add("Choosing a reliable provider with good work ethic is hard work in and of itself. Due diligence is inevitable. So, consider:\n" +
                "\n" +
                "Privacy and Logging – All VPNs promise privacy and anonymity. However, there are ways to tell the truth from lies. See below for more information on privacy and zero logs.\n" +
                "Security – The provider must explain the technology used to ensure your secure browsing. Is OpenVPN supported? How good is encryption?\n" +
                "Cross-platform – If you plan to use VPN on various devices, desktop, and mobile, inquire into the availability of native apps. If there is no native app, will you be able to set up the open-source OpenVPN client?\n" +
                "Speed – Test, test, and test. Speeds, when using a VPN, differ greatly and depend on a variety of factors – your ISP speed, the location of remote servers, server load, and more.");
        List<String> faq4 = new ArrayList<String>();
        faq4.add("You need to understand the difference between usage logs and connection logs.\n" +
                "\n" +
                "Connection Logs:\n" +
                "\n" +
                "Metadata about your connection, such as\n" +
                "The time you connect to a VPN\n" +
                "For how long you are connected\n" +
                "How often you connect to a VPN\n" +
                "Amount of data consumed\n" +
                "Usage Logs:\n" +
                "\n" +
                "Your online activity while connected to a VPN\n" +
                "Websites you visit\n" +
                "Identities you use.\n" +
                "See the difference? Usage logs are the most compromising. Some providers keep connection logs for a limited time (a few hours to three days), which is a reasonable practice for troubleshooting.");

        List<String> faq5 = new ArrayList<String>();
        faq5.add("You want to ask providers a series of critical questions to see if they are transparent, or pinpoint if something seems off. So, yes, you need to read the document.\n" +
                "\n" +
                "Many providers claim to keep zero logs while in reality they are tracking and profiling their users.  Often, the lengthy Privacy Policies and Terms of Service (ToS) weave a complex web of shady legalese that does explain the extent of logging they deploy. In this case, providers hope users never read them." +
                "But since you agree to the ToS and Privacy Policy, you can’t blame the provider for lying.");

        List<String> faq6 = new ArrayList<String>();
        faq6.add("No.\n" +
                "Many providers advertise complete anonymity online. What they’re not telling you is:\n" +
                "\n" +
                "Their ToS and Privacy Policy contain the fine print that suggests logging.\n" +
                "Their Privacy Policy states “we don’t log” without going into the detail.");

        List<String> faq7 = new ArrayList<String>();
        faq7.add("Besides masking your IP address, a VPN also protects your data by encrypting it. In layman terms, encryption is data conversion from plain text anyone can read to cipher that can only be read by authorized users.\n" +
                "\n" +
                "Encryption does not prevent hackers from intercepting your data. Instead, it makes your data unreadable.\n" +
                "To encrypt your data, a VPN uses protocols – PPTP, L2TP, OpenVPN, SSTP, and IKEv2. While these are quite technical, you should know that:\n" +
                "\n" +
                "OpenVPN is the current golden standard for all privacy wonks out there.\n" +
                "Avoid PPTP at all costs.\n" +
                "L2TP IPSec is not recommended for use on mobile devices.");

        List<String> faq8 = new ArrayList<String>();
        faq8.add("You’re better off with a paid subscription because if a provider’s customer base is 90%+ free users, their monetization model is based on tracking you and selling your data.\n" +
                "\n" +
                "Many reputable VPNs offer free limited accounts to entice users into subscribing to their paid plans. For instance, if you need a VPN once in a blue moon, you might as well make do with such a limited account.\n" +
                "If you intend to use VPN on a daily basis, consider paid subscriptions.");
        List<String> faq9 = new ArrayList<String>();
        faq9.add("In most Western countries – yes.  In countries with authoritarian regimes – no.\n" +
                "Currently, a host of countries in the Middle East, as well as countries with authoritarian, pro-Muslim regimes, have banned the use of VPNs.\n" +
                "\n" +
                "China, North Korea, Saudi Arabia, India, Turkey, Iran, Russia, Thailand, and another couple of dozen of countries have banned VPNs." +
                "In these countries, using a VPN is illegal, but selling access to VPN can land you a prison sentence.");

        List<String> faq10 = new ArrayList<String>();
        faq10.add("If a provider explicitly permits P2P, and lists servers that support torrenting – you’re good. Do run a few security checks for DNS leaks to make sure your VPN does not leak your real IP address.\n" +
                "\n" +
                "When you are torrenting, everyone downloading the same file can see your IP. With VPN enabled, they will see your fake, VPN-provided, IP.");

        List<String> faq11 = new ArrayList<String>();
        faq11.add("In most cases, yes. VPNs encrypt the traffic between your device and VPN servers. So, hackers won’t be able to read your data." +
                "If you happen to connect to a fake Wi-Fi hotspot some entrepreneurial hackers set up in public places, and they manage to intercept your traffic, all they will see is encrypted gibberish.");

        List<String> faq12 = new ArrayList<String>();
        faq12.add("First of all, to use a VPN you need to have a stable Internet connection with your ISP. You can’t have a Dial-Up connection. Assuming your base connection speeds are decent, some factors may affect your speeds when you connect to a VPN:\n" +
                "\n" +
                "If you use an encrypted connection, such as OpenVPN 256-bit AES, your speeds will drop because encryption requires processing power and time. The stronger the encryption, the greater is the speed drop.\n" +
                "If you connect to servers located at a great distance from where you physically are, your speeds will drop even greater. The further the server, the slower your speed.");

        expandableListDetail.put("What is a VPN?", faq1);
        expandableListDetail.put("Are VPNs Secure?", faq2);
        expandableListDetail.put("How to Choose a VPN?", faq3);
        expandableListDetail.put("What Does “Zero Logs” Mean?", faq4);
        expandableListDetail.put("Do I Need to Read a VPN’s Privacy Policy?", faq5);
        expandableListDetail.put("Am I Anonymous When Browsing Through a VPN?", faq6);
        expandableListDetail.put("What Are the Basics Of VPN Encryption?", faq7);
        expandableListDetail.put("Commercial VPNs vs Free VPNs", faq8);
        expandableListDetail.put("Are VPNs Legal?", faq9);
        expandableListDetail.put("Is Torrenting Through a VPN Safe?", faq10);
        expandableListDetail.put("Am I Safe When Using Public Wi-Fi Hotspots with a VPN?",faq11);
        expandableListDetail.put("Why Do Connection Speeds Drop When I Connect to a VPN?",faq12);






        return expandableListDetail;
    }
}
