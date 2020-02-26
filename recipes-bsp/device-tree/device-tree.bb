SUMMARY = "Hilscher BSP device trees"
DESCRIPTION = "Hilscher BSP device trees from within layer."
SECTION = "bsp"

# the device trees from within the layer are licensed as MIT, kernel includes are GPL
LICENSE = "MIT & GPLv2"
LIC_FILES_CHKSUM = " \
	file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
	file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
"

inherit devicetree

inherit dts-sign
# Setup public key patching into dts
DTS_SIGN_ENFORCE="${PLATFORM_SIGN}"
DTS_SIGN_KEY_DIR="${PLATFORM_KEYDIR}"
DTS_SIGN_KEY_NAME="${PLATFORM_KEYNAME}"
DTS_TO_SIGN="${WORKDIR}/src/${MACHINE}.dts"
do_unpack[vardeps] += "PLATFORM_SIGN PLATFORM_KEYDIR PLATFORM_KEYNAME"

S = "${WORKDIR}/src"

devicetree_do_install_append() {
	default_dtb=${KERNEL_DEVICETREE:-${MACHINE}}

	install -d ${D}/boot/dt-overlays
	for DTB_FILE in `ls *.dtbo`; do
		mv ${D}/boot/devicetree/${DTB_FILE} ${D}/boot/dt-overlays
	done

	find ${D}/boot/devicetree ! -name "${default_dtb}.dtb" -type f -exec rm -f {} +
}

devicetree_do_deploy() {
	cp -a ${D}/boot/* ${DEPLOYDIR}
}

FILES_${PN} += "boot/dt-overlays"

# --------------------------------------
# common include files

SRC_URI_netx4000 = " \
	file://src/netx4000-common.dtsi \
	file://src/dt-bindings/pinctrl/netx4000.h \
	file://src/dt-bindings/video/netx4000.h \
	${DTS_BASE} \
"

# --------------------------------------
# nxhx4000-jtag-plus-revX boards

COMPATIBLE_MACHINE_nxhx4000-jtag-plus-revX = ".*"
DTS_BASE_nxhx4000-jtag-plus-revX += " \
	file://src/nxhx4000-jtag-plus-revX.dts \
	file://src/adc0.dtso \
	file://src/adc1.dtso \
	file://src/i2c1.dtso \
	file://src/nxhx4000-rev3-spi0.dtso \
	file://src/can0.dtso \
	file://src/uart1.dtso \
"

SRC_URI_append_nxhx4000-jtag-plus-rev4 += " \
	file://src/nxhx4000-jtag-plus-rev4.dts \
"

SRC_URI_append_nxhx4000-jtag-plus-rev5 += "\
	file://src/nxhx4000-jtag-plus-rev5.dts \
"

# --------------------------------------
# ndeb4000-revX based boards

COMPATIBLE_MACHINE_ndeb4000-revX = ".*"
DTS_BASE_ndeb4000-revX += " \
	file://src/uart1.dtso \
	file://src/can0.dtso \
	file://src/mdio0.dtso \
	file://src/gmac0.dtso \
	file://src/gmac1.dtso \
	file://src/pcie-rc.dtso \
	file://src/adc0.dtso \
	file://src/adc1.dtso \
"

SRC_URI_append_ndeb4000-rev1 = "\
	file://src/ndcm4000-rev1.dts \
	file://src/ndeb4000-rev1.dts \
"

# --------------------------------------
# ndcm4000-revX based boards

COMPATIBLE_MACHINE_ndcm4000-revX = ".*"
DTS_BASE_ndcm4000-revX += " \
        file://src/uart1.dtso \
        file://src/can0.dtso \
        file://src/mdio0.dtso \
        file://src/gmac0.dtso \
        file://src/gmac1.dtso \
        file://src/pcie-rc.dtso \
        file://src/adc0.dtso \
        file://src/adc1.dtso \
"

SRC_URI_append_ndcm4000-rev1 = "\
	file://src/ndcm4000-rev1.dts \
"

# --------------------------------------
# netxflex4000-revX boards

COMPATIBLE_MACHINE_netflex4000-revX = ".*"
DTS_BASE_netflex4000-revX += " \
"

SRC_URI_append_netflex4000-rev1 = "\
	file://src/netflex4000-rev1.dts \
"
