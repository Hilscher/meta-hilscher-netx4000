#ifndef _DT_BINDINGS_PINCTRL_NETX4000_H
#define _DT_BINDINGS_PINCTRL_NETX4000_H

#define REEMUX_0  (0 << SRT_PORTCONTROL_CONFIGURATION_SEL)
#define REEMUX_1  (1 << SRT_PORTCONTROL_CONFIGURATION_SEL)
#define REEMUX_2  (2 << SRT_PORTCONTROL_CONFIGURATION_SEL)
#define REEMUX_3  (3 << SRT_PORTCONTROL_CONFIGURATION_SEL)
#define REEMUX_4  (4 << SRT_PORTCONTROL_CONFIGURATION_SEL)

#define REEMUX_DRV_04MA  (0 << SRT_PORTCONTROL_CONFIGURATION_DRV)
#define REEMUX_DRV_06MA  (1 << SRT_PORTCONTROL_CONFIGURATION_DRV)
#define REEMUX_DRV_08MA  (2 << SRT_PORTCONTROL_CONFIGURATION_DRV)
#define REEMUX_DRV_12MA  (3 << SRT_PORTCONTROL_CONFIGURATION_DRV)

#define REEMUX_UDC_NONE         (0 << SRT_PORTCONTROL_CONFIGURATION_UDC)
#define REEMUX_UDC_PULLUP50K    (1 << SRT_PORTCONTROL_CONFIGURATION_UDC)
#define REEMUX_UDC_NONE2        (2 << SRT_PORTCONTROL_CONFIGURATION_UDC)
#define REEMUX_UDC_PULLDOWN50K  (3 << SRT_PORTCONTROL_CONFIGURATION_UDC)

#define REEMUX_CTL(a)  ((a) << SRT_PORTCONTROL_CONFIGURATION_CTL)

#define SRT_PORTCONTROL_CONFIGURATION_UDC   0
#define SRT_PORTCONTROL_CONFIGURATION_DRV   4
#define SRT_PORTCONTROL_CONFIGURATION_CTL   8
#define SRT_PORTCONTROL_CONFIGURATION_SEL   12

#define MUX_PIN(major,minor) ((major * 16 + minor) * 4)

#endif
